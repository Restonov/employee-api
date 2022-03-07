package com.restonov.task.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.restonov.task.domain.EmployeeCategory;
import com.restonov.task.dto.EmployeeCategoryDto;
import com.restonov.task.mapper.EmployeeCategoryMapper;
import com.restonov.task.service.impl.EmployeeCategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories", produces = APPLICATION_JSON_VALUE)
public class EmployeeCategoryController {

  private final EmployeeCategoryService categoryService;
  private final EmployeeCategoryMapper mapper;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public EmployeeCategoryDto createCategory(@RequestBody EmployeeCategoryDto dto) {
    EmployeeCategory category = categoryService.create(mapper.dtoToEntity(dto));
    return mapper.entityToDto(category);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EmployeeCategoryDto> findAllCategories() {
    return mapper.entityToDto(categoryService.findAll());
  }

  @GetMapping(path = "/{id}")
  public EmployeeCategoryDto findCategoryById(@PathVariable Long id) {
    return mapper.entityToDto(categoryService.findById(id));
  }

  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public EmployeeCategoryDto updateCategory(@PathVariable Long id,
      @RequestBody EmployeeCategoryDto dto) {
    final EmployeeCategory updatedCategory = categoryService.update(id, mapper.dtoToEntity(dto));
    return mapper.entityToDto(updatedCategory);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
    categoryService.delete(id);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }
}
