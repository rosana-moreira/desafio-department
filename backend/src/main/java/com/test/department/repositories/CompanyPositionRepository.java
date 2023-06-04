package com.test.department.repositories;

import com.test.department.entities.CompanyPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyPositionRepository extends JpaRepository<CompanyPosition, Long> {
}
