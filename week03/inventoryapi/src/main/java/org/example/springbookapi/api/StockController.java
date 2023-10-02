package org.example.springbookapi.api;

import org.example.springbookapi.entity.Stock;
import org.example.springbookapi.entity.StockRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StockController {

    private final StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/invokeStock")
    public List<Stock> invoke(@RequestParam Map<String, Object> params) throws InterruptedException {
        SearchSpecification<Stock> query = new SearchSpecification<>(params);
        List<Stock> results = stockRepository.findAll(query);
        return results;
    }
}
