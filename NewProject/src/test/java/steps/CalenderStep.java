package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import pages.CalendarActions;


import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class CalenderStep {
    @Steps
    CalendarActions calendarActions = new CalendarActions();

    // Get event
    @When("I want to get event from {string} to {string}")
    public void iWantToGetEventTypeIs(String from, String to) {
        calendarActions.sortByType(from, to);
        Serenity.setSessionVariable("calendarId").to(lastResponse().path("data._id[0]"));
    }

    @Then("Check response code when get event by type")
    public void checkResponse() {
        restAssuredThat(response -> response.statusCode(200));
    }

    // Create event has schedule is Once/ Weekly/ Monthly
    @When("Create event {string},{string},{string}")
    public void createEvent(String title, String type, String scheduleType) {
        calendarActions.createEvent(title,type,scheduleType);
    }
    @Then("Check response code when create Once, Weekly, Monthly event")
    public void checkResponseCodeWhenCreateEventOnce() {
        restAssuredThat(response -> response.statusCode(201));
    }

    // Create event has schedule is Daily
    @When("Create daily event {string},{string},{string}")
    public void createDailyEvent(String title, String type, String scheduleType) {
        calendarActions.createDailyEvent(title,type,scheduleType);
    }
    @Then("Check response code when create daily event")
    public void checkResponseCodeWhenCreateDailyEvent() {
        restAssuredThat(response -> response.statusCode(201));
    }

    // Create recurrence event
    @When("Create recurrence event {string},{string},{string}")
    public void createRecurrenceEvent(String title, String type, String recurrence) {
        calendarActions.createRecurrenceEvent(title,type,recurrence);
    }
    @Then("Check response code when create recurrence event")
    public void checkResponseCodeWhenCreateRecurrenceEvent() {
        restAssuredThat(response -> response.statusCode(201));
    }

    //Create event add attendees, department, role and thread
    @When("Create event with {string},{string},{string},{string},{string},{string},{string}")
    public void createEventWith(String title, String type, String scheduleType, String attendees_id,
                                String to_department_ids, String to_role_ids, String to_thread_ids) {
        calendarActions.createAtteendeesEvent(title,type,scheduleType,attendees_id,to_department_ids,
                to_role_ids,to_thread_ids);
    }

    @Then("Check response code when event has attendees")
    public void checkResponseCodeWhenEventHasAttendees() {
        restAssuredThat(response -> response.statusCode(201));
    }


    //Edit event
    @When("Edit event to {string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void editEventTo(String title, String scheduleType, String value, String start_hour, String start_minute,
                            String end_hour, String end_minute, String identities, String method) {
        String idEvent = Serenity.sessionVariableCalled("calendarId");
        calendarActions.editEvent(title, scheduleType, value, start_hour, start_minute, end_hour, end_minute, identities, method, idEvent);

    }

    @Then("Check response code when edit event")
    public void checkResponseCodeWhenEditEvent() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @When("Delete event")
    public void deleteEvent() {
        String idEvent = Serenity.sessionVariableCalled("calendarId");
        calendarActions.deleteEvent(idEvent);
    }

    @Then("Check response code when delete event")
    public void checkResponseCodeWhenDeleteEvent() {
        restAssuredThat(response -> response.statusCode(200));
    }

}
