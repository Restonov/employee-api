package com.restonov.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.restonov.task.domain")
@EnableJpaRepositories("com.restonov.task.dao")
@SpringBootApplication(scanBasePackages = "com.restonov.task")
public class SpringAppStarter {

  public static void main(String[] args) {
    SpringApplication.run(SpringAppStarter.class, args);
  }
}
