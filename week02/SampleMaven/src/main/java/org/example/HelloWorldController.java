package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    private final EntityRepository repository;

    public HelloWorldController(EntityRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/hello")
    public String hello() {
        InventoryEntity in = new InventoryEntity();
        in.setFirstName("Deepak");
        in.setLastName("Rajamohan");
        repository.save(in);
        return "Hello World RESTful with Spring Boot";
    }

    @GetMapping("/test")
    public List<InventoryEntity> getUsers() {
        return repository.findAll();
    }
}
