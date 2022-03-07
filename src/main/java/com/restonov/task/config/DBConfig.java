package com.restonov.task.config;

import static java.lang.String.format;

import de.flapdoodle.embed.process.runtime.Network;
import java.io.IOException;
import java.util.Arrays;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.AbstractPostgresConfig;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;
import ru.yandex.qatools.embed.postgresql.distribution.Version;

@Configuration
@EnableTransactionManagement
public class DBConfig {

  @Bean
  public PostgresConfig postgresConfig() throws IOException {
    // make it readable from configuration source file or system , it is hard coded here for explanation purpose only
    final PostgresConfig postgresConfig = new PostgresConfig(Version.V9_6_11,
        new AbstractPostgresConfig.Net("localhost", Network.getFreeServerPort()),
        new AbstractPostgresConfig.Storage("employees"),
        new AbstractPostgresConfig.Timeout(),
        new AbstractPostgresConfig.Credentials("user", "pass")
    );

    postgresConfig.getAdditionalInitDbParams().addAll(Arrays
        .asList("–nosync", "–locale=en_US.UTF-8"));
    return postgresConfig;
  }

  @Bean
  @DependsOn("postgresProcess")
  public DataSource dataSource(PostgresConfig config) {

    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName("org.postgresql.Driver");
    ds.setUrl(format("jdbc:postgresql://%s:%s/%s", config.net().host(), config.net().port(), config.storage().dbName()));
    ds.setUsername(config.credentials().username());
    ds.setPassword(config.credentials().password());
    return ds;
  }

  @Bean(destroyMethod = "stop")
  public PostgresProcess postgresProcess(PostgresConfig config) throws IOException {
    PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
    PostgresExecutable exec = runtime.prepare(config);
    return exec.start();
  }
}

