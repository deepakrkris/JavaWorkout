package org.example.springbookapi.api;

import org.example.springbookapi.entity.Reviews;
import org.example.springbookapi.entity.ReviewsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReviewsController {

    private final ReviewsRepository reviewsRepository;

    public ReviewsController(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @GetMapping("/invokeReviews")
    public List<Reviews> invoke(@RequestParam Map<String, Object> params) {
        SearchSpecification<Reviews> query = new SearchSpecification<>(params);
        return reviewsRepository.findAll(query);
    }
}
