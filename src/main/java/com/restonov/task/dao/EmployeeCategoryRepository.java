package com.restonov.task.dao;

import com.restonov.task.domain.EmployeeCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeCategoryRepository extends JpaRepository<EmployeeCategory, Long> {

  Optional<EmployeeCategory> findByName(String name);
}
