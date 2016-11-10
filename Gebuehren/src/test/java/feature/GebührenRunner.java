package feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber" }, 
                 glue = "feature",
                 features = "src/test/resources/feature/Gebühren.feature")
public class GebührenRunner {
 
    
}
