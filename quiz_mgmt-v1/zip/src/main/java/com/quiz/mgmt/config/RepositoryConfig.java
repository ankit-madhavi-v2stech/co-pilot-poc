package com.quiz.mgmt.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.quiz.mgmt.repository")
@EntityScan(basePackages = "com.quiz.mgmt.entity")
public class RepositoryConfig {
}
