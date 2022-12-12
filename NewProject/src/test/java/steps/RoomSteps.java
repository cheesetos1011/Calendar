package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.RoomActions;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class RoomSteps {
    @Steps
    RoomActions roomActions = new RoomActions();

    @When("I get information of rooms in my workspace")
    public void iGetInformationOfRoomsInMyWorkspace() {
        roomActions.getRoomId();
    }

    @Then("Check response code when get room's info")
    public void checkResponseCodeWhenGetRoomSInfo() {
        restAssuredThat(response -> response.statusCode(200));
    }
}
