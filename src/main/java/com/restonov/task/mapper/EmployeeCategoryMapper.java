package com.restonov.task.mapper;

import com.restonov.task.domain.EmployeeCategory;
import com.restonov.task.dto.EmployeeCategoryDto;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EmployeeCategoryMapper {

  EmployeeCategoryDto entityToDto(EmployeeCategory entity);
  List<EmployeeCategoryDto> entityToDto(List<EmployeeCategory> entities);

  @Mapping(target = "employee", ignore = true)
  EmployeeCategory dtoToEntity(EmployeeCategoryDto dto);
}
