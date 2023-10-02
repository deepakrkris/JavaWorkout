package org.example.springbookapi.entity;

import org.example.springbookapi.JsonFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class StartupCommand implements CommandLineRunner {
    private Logger log = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (stockRepository.count() == 0) {
            JsonFileReader fileReader = new JsonFileReader();
            List<Object> books = fileReader.parseJsonFile("stock.json", Stock.class);
            books.stream().forEach((book) -> {
                stockRepository.save((Stock) book);
            });
            log.info(">>>> " + books.size() + " Stock data Saved!");
        }

        if (salesRepository.count() == 0) {
            JsonFileReader fileReader = new JsonFileReader();
            List<Object> books = fileReader.parseJsonFile("sales.json", Sales.class);
            books.stream().forEach((book) -> {
                salesRepository.save((Sales) book);
            });
            log.info(">>>> " + books.size() + " Sales data Saved!");
        }

        if (reviewsRepository.count() == 0) {
            JsonFileReader fileReader = new JsonFileReader();
            List<Object> books = fileReader.parseJsonFile("reviews.json", Reviews.class);
            books.stream().forEach((book) -> {
                reviewsRepository.save((Reviews) book);
            });
            log.info(">>>> " + books.size() + " Reviews data Saved!");
        }
    }
}
