package me.erano.com;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//When an EmployeeNotFoundException is thrown, this extra tidbit of Spring MVC configuration is used to render an HTTP 404 error
// @RestControllerAdvice signals that this advice is rendered straight into the response body.
// @ExceptionHandler configures the advice to only respond when an EmployeeNotFoundException is thrown.
// @ResponseStatus says to issue an HttpStatus.NOT_FOUND — that is, an HTTP 404 error.
// The body of the advice generates the content. In this case, it gives the message of the exception.
@RestControllerAdvice
public class EmployeeNotFoundAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}
