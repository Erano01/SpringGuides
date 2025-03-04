package me.erano.com.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController = @Controller + @ResponseBody
public class HelloController {

    //type to cmd -> curl http://localhost:8080
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
