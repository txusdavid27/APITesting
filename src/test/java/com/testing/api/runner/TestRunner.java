package com.testing.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.testing.api.stepDefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = true,
        plugin = {"pretty:target/cucumber/cucumber.txt",
                  "html:target/cucumber/report",
                  "json:target/cucumber.json"}
)
public class TestRunner {
}
