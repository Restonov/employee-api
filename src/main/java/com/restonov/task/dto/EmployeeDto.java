package com.restonov.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

  private long id;
  private String name;
  private EmployeeCategoryDto categoryDto;
}
