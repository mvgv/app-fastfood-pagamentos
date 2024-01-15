package br.com.appfastfoodpagamentos.config;

import br.com.appfastfoodpagamentos.AppFastfoodPagamentosApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= AppFastfoodPagamentosApplication.class)
public class CucumberSpringConfiguration {
}