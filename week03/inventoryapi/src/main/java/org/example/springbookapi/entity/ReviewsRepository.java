package org.example.springbookapi.entity;

import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, Integer>, JpaSpecificationExecutor<Reviews> {
    public List<Reviews> findAll(Specification<Reviews> spec, JpaSort sort);
}
