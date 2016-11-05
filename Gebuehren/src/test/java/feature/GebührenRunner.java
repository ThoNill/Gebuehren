package feature;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber" }, 
                 glue = "feature",
                 features = "src/test/resources/feature/Geb�hren.feature")
public class Geb�hrenRunner {
 
    
}
