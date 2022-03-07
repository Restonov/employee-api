package com.restonov.task.mapper;

import com.restonov.task.domain.EmployeeCategory;
import com.restonov.task.dto.EmployeeCategoryDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeCategoryMapper {

  EmployeeCategoryDto entityToDto(EmployeeCategory entity);
  List<EmployeeCategoryDto> entityToDto(List<EmployeeCategory> entities);
  EmployeeCategory dtoToEntity(EmployeeCategoryDto dto);
}
