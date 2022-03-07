package com.restonov.task.web;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.restonov.task.domain.Employee;
import com.restonov.task.dto.EmployeeDto;
import com.restonov.task.mapper.EmployeeMapper;
import com.restonov.task.service.impl.EmployeeService;
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
@RequestMapping(value = "/employees", produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeMapper mapper;

  @PostMapping(consumes = APPLICATION_JSON_VALUE)
  public EmployeeDto createEmployee(@RequestBody EmployeeDto dto) {
    Employee employee = employeeService.create(mapper.dtoToEntity(dto));
    return mapper.entityToDto(employee);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<EmployeeDto> findAllEmployees() {
    return mapper.entityToDto(employeeService.findAll());
  }

  @GetMapping(path = "/{id}")
  public EmployeeDto findEmployeeById(@PathVariable Long id) {
    return mapper.entityToDto(employeeService.findById(id));
  }

  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto dto) {
    final Employee updatedEmployee = employeeService.update(id, mapper.dtoToEntity(dto));
    return mapper.entityToDto(updatedEmployee);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
    employeeService.delete(id);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .build();
  }
}
