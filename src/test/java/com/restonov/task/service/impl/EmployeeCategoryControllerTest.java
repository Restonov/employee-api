package com.restonov.task.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.restonov.task.dto.EmployeeDto;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
class EmployeeCategoryControllerTest {

  @Autowired
  private TestRestTemplate template;

  @Value("${admin.name}")
  private String adminName;

  @Value("${admin.password}")
  private String adminPass;

  @Test
  void testFindAll() {
    ResponseEntity<List<EmployeeDto>> result = template.withBasicAuth(adminName, adminPass)
        .exchange("/api/categories", HttpMethod.GET,
        null, new ParameterizedTypeReference<>() {
        });

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(adminName, Objects.requireNonNull(result.getBody()).get(0).getName());
  }

  @Test
  void testFindById() {
    ResponseEntity<EmployeeDto> result = template.withBasicAuth(adminName, adminPass)
        .getForEntity("/api/categories/1",
        EmployeeDto.class);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(adminName, Objects.requireNonNull(result.getBody()).getName());
  }

  @Test
  void testDeleteById() {
    ResponseEntity<Void> result = template.withBasicAuth(adminName, adminPass)
        .exchange("/api/categories/1", HttpMethod.DELETE, null,
        Void.class);

    assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
  }
}


