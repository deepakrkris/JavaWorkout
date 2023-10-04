package org.example.springbookapi.entity;

import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer>, JpaSpecificationExecutor<Sales> {
    public List<Sales> findAll(Specification<Sales> spec, JpaSort sort);
}
