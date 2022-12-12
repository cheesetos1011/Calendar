package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.ChatActions;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class ChatStep {
    @Steps
    ChatActions chatActions = new ChatActions();

    @When("I get list thread chat")
    public void iGetListThreadChat() {
        chatActions.getThread();
    }

    @Then("Check response code when get list thread")
    public void checkResponseCodeWhenGetListThread() {
        restAssuredThat(response -> response.statusCode(200));
    }
}
