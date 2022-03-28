package com.restonov.task.mapper;

import com.restonov.task.domain.Employee;
import com.restonov.task.dto.EmployeeDto;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EmployeeCategoryMapper.class, builder = @Builder(disableBuilder = true))
public interface EmployeeMapper {

  EmployeeDto entityToDto(Employee entity);
  List<EmployeeDto> entityToDto(List<Employee> entities);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "authorities", ignore = true)
  Employee dtoToEntity(EmployeeDto dto);
}
