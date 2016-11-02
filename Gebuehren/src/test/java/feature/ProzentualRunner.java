package feature;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber" }, 
                 glue = "feature",
                 features = "/home/javaman/git/Gebuehren/Gebuehren/src/test/resources/feature/Prozentual.feature")
public class ProzentualRunner {
 
    
}
