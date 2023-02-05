package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class StartupCommand implements CommandLineRunner {
    private Logger log = Logger.getLogger(String.valueOf(getClass()));
    
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            JsonFileReader fileReader = new JsonFileReader();
            List<Object> books = fileReader.parseJsonFile("data.json", BookEntity.class);
            books.stream().forEach((book) -> {
                bookRepository.save((BookEntity) book);
            });
            log.info(">>>> " + books.size() + " Books Saved!");
        }
    }
}
