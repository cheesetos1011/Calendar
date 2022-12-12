package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.OrgChartActions;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class OrgChartStep {
    @Steps
    OrgChartActions orgChartActions = new OrgChartActions();

    @When("I get list department in my workspace")
    public void iGetListDepartmentInMyWorkspace() {
        orgChartActions.getDepartment();
    }

    @Then("Check response code when get list department")
    public void checkResponseCodeWhenGetListDepartment() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @When("I get list role in my workspace")
    public void iGetListRoleInMyWorkspace() {
        orgChartActions.getRole();
    }

    @Then("Check response code when get list role")
    public void checkResponseCodeWhenGetListRole() {
        restAssuredThat(response -> response.statusCode(200));
    }
}
