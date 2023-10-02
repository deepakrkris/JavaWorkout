package org.example.springbookapi.api;

import org.example.springbookapi.entity.Sales;
import org.example.springbookapi.entity.SalesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SalesController {

    private final SalesRepository salesRepository;

    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @GetMapping("/invokeSales")
    public List<Sales> invoke(@RequestParam Map<String, Object> params) {
        SearchSpecification<Sales> query = new SearchSpecification<>(params);
        return salesRepository.findAll(query);
    }
}
