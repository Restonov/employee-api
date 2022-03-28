package com.restonov.task.service.impl;


import com.restonov.task.dao.EmployeeCategoryRepository;
import com.restonov.task.domain.EmployeeCategory;
import com.restonov.task.exception.ServiceException;
import com.restonov.task.service.CrudService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeCategoryService implements CrudService<EmployeeCategory, Long> {

  private static final String CATEGORY_WITH_ID_IS_NOT_FOUND = "Employee category with id %s is not found";

  private final EmployeeCategoryRepository categoryRepository;

  @Override
  public EmployeeCategory create(EmployeeCategory category) {
    return categoryRepository.findByName(category.getName())
        .orElseGet(() -> categoryRepository.save(category));
  }

  @Override
  @Cacheable(value = "categories")
  public List<EmployeeCategory> findAll() {
    return categoryRepository.findAll();
  }

  @Override
  public EmployeeCategory findById(Long id) {
    Optional<EmployeeCategory> categoryOptional = categoryRepository.findById(id);
    return categoryOptional.orElseThrow(() -> {
      throw new ServiceException(String.format(CATEGORY_WITH_ID_IS_NOT_FOUND, id));
    });
  }

  @Override
  @Transactional
  public EmployeeCategory update(Long id, EmployeeCategory category) {
    Optional<EmployeeCategory> categoryOptional = categoryRepository.findById(id);
    return categoryOptional.map(e -> {
      category.setId(id);
      return categoryRepository.save(category);
    }).orElseThrow(() -> {
      throw new ServiceException(String.format(CATEGORY_WITH_ID_IS_NOT_FOUND, id));
    });
  }

  @Override
  public void delete(Long id) {
    Optional<EmployeeCategory> categoryOptional = categoryRepository.findById(id);
    categoryOptional.ifPresentOrElse(categoryRepository::delete,
        () -> {
          throw new ServiceException(String.format(CATEGORY_WITH_ID_IS_NOT_FOUND, id));
        }
    );
  }
}
