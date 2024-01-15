package br.com.appfastfoodpagamentos;


import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("br.com.appfastfoodpagamentos.stepdefinitions")
@Cucumber
public class CucumberRunnerTest {
}