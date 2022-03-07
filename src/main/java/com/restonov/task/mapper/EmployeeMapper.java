package com.restonov.task.mapper;

import com.restonov.task.domain.Employee;
import com.restonov.task.dto.EmployeeDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDto entityToDto(Employee entity);
  List<EmployeeDto> entityToDto(List<Employee> entities);
  Employee dtoToEntity(EmployeeDto dto);
}
