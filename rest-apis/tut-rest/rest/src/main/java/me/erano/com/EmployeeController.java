package me.erano.com;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
//This tutorial is based on Spring MVC and uses the static helper methods from WebMvcLinkBuilder to build these links. If you are using Spring WebFlux in your project, you must instead use WebFluxLinkBuilder.
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

// @RestController indicates that the data returned by each method is written straight into the response body instead of rendering a template.
// An EmployeeRepository is injected by constructor into the controller.
// We have routes for each operation (@GetMapping, @PostMapping, @PutMapping and @DeleteMapping, corresponding to
// HTTP GET, POST, PUT, and DELETE calls). (We recommend reading each method and understanding what they do.)
// mployeeNotFoundException is an exception used to indicate when an employee is looked up but not found.
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    /*
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll();
    }
     */
    // end::get-aggregate-root[]

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(employee -> EntityModel.of(employee,
                        linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item
    /*
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
    */

    // A critical ingredient to any RESTful service is adding links to relevant operations.
    // To make your controller more RESTful, add links like the following to the existing one method in EmployeeController
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {
        //The return type of the method has changed from Employee to EntityModel<Employee>. EntityModel<T> is a generic container from Spring HATEOAS that includes not only the data but a collection of links.

        Employee employee = repository.findById(id) //
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        // linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel() asks that Spring HATEOAS build a link to the one method of EmployeeController and flag it as a self link.
        // linkTo(methodOn(EmployeeController.class).all()).withRel("employees") asks Spring HATEOAS to build a link to the aggregate root, all(), and call it "employees".
        return EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
