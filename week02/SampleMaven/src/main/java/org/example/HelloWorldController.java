package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    private final EntityRepository repository;
    private final BookRepository books;

    public HelloWorldController(EntityRepository repository, BookRepository books) {
        this.repository = repository;
        this.books = books;
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

    @GetMapping("/books")
    public List<BookEntity> getBooks() {
        return books.findAll();
    }
}
