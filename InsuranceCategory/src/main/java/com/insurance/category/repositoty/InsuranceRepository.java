package com.insurance.category.repositoty;

import com.insurance.category.model.CategoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<CategoryData, Integer> {
    public CategoryData findByinsuranceCode();
}
