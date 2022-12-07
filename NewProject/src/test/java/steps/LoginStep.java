package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.LoginActions;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class LoginStep {
    @Steps
    LoginActions loginActions = new LoginActions();

    @When("I check email {string}")
    public void iCheckEmail(String email) {
        loginActions.checkEmail(email);
    }

    @When("I use {string} and {string} to login")
    public void iUseAndToLogin(String email, String pass) {
        loginActions.checkLogin(email, pass);
    }

    @Then("Check status code")
    public void checkStatusCode() {
        restAssuredThat(response -> response.statusCode(200));
    }

}
