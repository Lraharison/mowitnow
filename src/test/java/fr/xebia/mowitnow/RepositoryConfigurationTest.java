package fr.xebia.mowitnow;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "fr.xebia.mowitnow.model" })
@EnableJpaRepositories(basePackages = { "fr.xebia.mowitnow.dao" })
@EnableTransactionManagement
public class RepositoryConfigurationTest {

}
