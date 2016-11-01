package test;

import abrechnung.Abrechnung;
import beans.Konto;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;

public class ProzentualSteps {
    Konto gebührKonto = new TestKonto(2, "Gebühr");
    Konto betragKonto = new TestKonto(3, "Betrag");
    TestRepository repo = new TestRepository();
    Abrechnung abrechnung;
    
    public ProzentualSteps() {
        super();
        abrechnung = new Abrechnung(repo);
    }

    @Given("^Mit einer prozentualen Gebühr (\\d+)$")
    public void i_have_a_calculator(double prozentsatz) throws Throwable {
        repo.setProzentsatz(prozentsatz);
    }

    @When("^und einem Betrag (\\d+)$")
    public void i_add_and(long cent) throws Throwable {
        repo.setBetrag(cent);
    }

    @Then("^ist die Gebühr (\\d+)$")
    public void the_result_should_be(int arg1) throws Throwable {
    }

}
