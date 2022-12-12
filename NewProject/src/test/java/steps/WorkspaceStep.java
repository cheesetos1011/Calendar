package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.WorkspaceActions;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class WorkspaceStep {
    @Steps
    WorkspaceActions workspaceActions = new WorkspaceActions();

    @When("I get my information to get id of workspace")
    public void iGetMyInformationToGetIdOfWorkspace() {
        workspaceActions.getWpID();
    }

    @Then("Check response code when get my information")
    public void checkResponseCodeWhenGetMyInformation() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @When("I get list active member")
    public void iGetListActiveMember() {
    }

    @Then("Check response code when get list active member")
    public void checkResponseCodeWhenGetListActiveMember() {
    }
}
