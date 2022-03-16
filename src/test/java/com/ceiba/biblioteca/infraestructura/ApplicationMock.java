package com.ceiba.biblioteca.infraestructura;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.ceiba.biblioteca"})
@EnableJpaRepositories(basePackages = "com.ceiba.biblioteca")
public class ApplicationMock {

}