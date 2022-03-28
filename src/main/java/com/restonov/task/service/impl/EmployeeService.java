package com.restonov.task.service.impl;

import com.restonov.task.dao.EmployeeRepository;
import com.restonov.task.domain.Employee;
import com.restonov.task.exception.ServiceException;
import com.restonov.task.service.CrudService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService implements CrudService<Employee, Long>, UserDetailsService {

  private static final String EMPLOYEE_IS_NOT_FOUND = "Employee with id %s is not found";

  private final EmployeeRepository employeeRepository;

  @Override
  public Employee create(Employee employee) {
    return employeeRepository.save(employee);
  }

  @Override
  @Cacheable("employees")
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee findById(Long id) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    return employeeOptional.orElseThrow(() -> {
      throw new ServiceException(String.format(EMPLOYEE_IS_NOT_FOUND, id));
    });
  }

  @Override
  @Transactional
  public Employee update(Long id, Employee employee) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    return employeeOptional.map(e -> {
      e.setName(employee.getName());
      e.setCategory(employee.getCategory());
      return employeeRepository.save(e);
    }).orElseThrow(() -> {
      throw new ServiceException(String.format(EMPLOYEE_IS_NOT_FOUND, id));
    });
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    employeeOptional.ifPresentOrElse(employeeRepository::delete,
        () -> {
          throw new ServiceException(String.format(EMPLOYEE_IS_NOT_FOUND, id));
        }
    );
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return employeeRepository.findByName(username).orElseThrow(() -> {
      throw new UsernameNotFoundException("Employee with such username is not found");
    });
  }
}
