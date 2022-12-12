package pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.Calendar.Option;
import model.Calendar.ScheduleObject;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

import java.util.*;

import static constant.CalendarValue.*;
import static org.hamcrest.Matchers.equalTo;
import static pages.LoginActions.token;
import static pages.RoomActions.roomId;
import static pages.WorkspaceActions.wpId;

public class CalendarActions{

    @Step("Get event by type")
    public void sortByType(String from, String to) {
        SerenityRest.given().contentType(ContentType.JSON)
                .header("content-type","application/json")
                .auth().oauth2(token)
                .queryParam("event_from_at",from)
                .queryParam("event_to_at",to)
                .when()
                    .get("https://api.gapowork.vn/calendar/v1.0/events")
                .then().extract().response().prettyPrint();
    }


    @Step("Create new event: schedule type is Once or Weekly or Monthly")
    public void createEvent(String title, String type, String scheduleType,String description, String remindBefore) {
        List<Long> valueArray = new ArrayList<>();
        if(scheduleType.equals("Once")) {
            valueArray.add(CurrentTimestampValue);
        } else if(scheduleType.equals("Weekly")) {
            valueArray.add(weeklyValue);
        } else
            valueArray.add(monthlyValue);

        List<Integer> remind_before = new ArrayList<>();
        remind_before.add(Integer.parseInt(remindBefore));

        ScheduleObject schedule = new ScheduleObject();
        schedule.setType(scheduleType);
        schedule.setValue(valueArray);
        schedule.setRemind_before(remind_before);
        schedule.setStart_hour(currentHour);
        schedule.setStart_minute(currentMintute);
        schedule.setEnd_hour(endHour);
        schedule.setEnd_minute(endMinute);

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("title",title);
        requestBody.put("type",type);
        requestBody.put("description",description);
        requestBody.put("schedule",schedule);

        Response res = SerenityRest.given()
                .header("Content-Type","application/json")
                .auth().oauth2(token)
                .when()
                .body(requestBody)
                .post("https://api.gapowork.vn/calendar/v1.0/events");
//                .then().assertThat().body("data.title",equalTo(title));
        res.then().assertThat().body("data.title",equalTo(title));
        res.then().assertThat().body("data.description",equalTo(description));
        res.then().assertThat().body("data.schedule.start_hour",equalTo(currentHour));
        res.then().assertThat().body("data.schedule.start_minute",equalTo(currentMintute));
        res.then().assertThat().body("data.schedule.end_hour",equalTo(endHour));
        res.then().assertThat().body("data.schedule.end_minute",equalTo(endMinute));
        res.then().assertThat().body("data.schedule.remind_before",equalTo(remind_before));
    }

    @Step("Create new event: schedule type is Daily")
    public void createDailyEvent(String title, String type, String scheduleType) {
        boolean isAllDay;
        ScheduleObject schedule = new ScheduleObject();
        schedule.setType(scheduleType);
        schedule.setStart_hour(currentHour);
        schedule.setStart_minute(currentMintute);
        schedule.setEnd_hour(endHour);
        schedule.setEnd_minute(endMinute);
        if(type.equals("Reminder")){
            isAllDay = false;
        }
        else isAllDay = true;
        schedule.setIs_all_day(isAllDay);

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("title",title);
        requestBody.put("type",type);
        requestBody.put("schedule",schedule);

        Response res = SerenityRest.given()
                .header("Content-Type","application/json")
                .auth().oauth2(token)
                .when()
                .body(requestBody)
                .post("https://api.gapowork.vn/calendar/v1.0/events");

        res.then().assertThat().body("data.schedule.is_all_day",equalTo(isAllDay));
    }

    @Step("Create new event: Recurrence event")
    public void createRecurrenceEvent(String title, String type, String recurrence) {
        ScheduleObject schedule = new ScheduleObject();
        schedule.setType("Daily");
        schedule.setStart_hour(currentHour);
        schedule.setStart_minute(currentMintute);
        schedule.setEnd_hour(endHour);
        schedule.setEnd_minute(endMinute);
        schedule.setRecurrence(recurrence);

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("title",title);
        requestBody.put("type",type);
        requestBody.put("schedule",schedule);

        SerenityRest.given()
                .header("Content-Type","application/json")
                .auth().oauth2(token)
                .when()
                .body(requestBody)
                .post("https://api.gapowork.vn/calendar/v1.0/events")
                .then().extract().response().prettyPrint();
    }

    @Step("Create room event")
    public void roomEvent(String type, String scheduleType,String hasMeeting) {
        List<Long> valueArray = new ArrayList<>();
        if(scheduleType.equals("Once")) {
            valueArray.add(CurrentTimestampValue);
        } else if(scheduleType.equals("Weekly")) {
            valueArray.add(weeklyValue);
        } else
            valueArray.add(monthlyValue);

        ScheduleObject schedule = new ScheduleObject();
        schedule.setType(scheduleType);
        schedule.setValue(valueArray);
        schedule.setStart_hour(currentHour);
        schedule.setStart_minute(currentMintute);
        schedule.setEnd_hour(endHour);
        schedule.setEnd_minute(endMinute);

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("room_id",roomId);
        requestBody.put("type",type);
        requestBody.put("has_meeting",Boolean.parseBoolean(hasMeeting));
        requestBody.put("schedule",schedule);

        Response res = SerenityRest.given()
                .header("Content-Type","application/json")
                .header("x-gapo-workspace-id",wpId)
                .auth().oauth2(token)
                .when()
                .body(requestBody)
                .post("https://api.gapowork.vn/calendar/v1.0/events");
        res.then().extract().response().prettyPrint();
        res.then().assertThat().body("data.room_id",equalTo(roomId));
        res.then().assertThat().body("data.has_meeting",equalTo(Boolean.parseBoolean(hasMeeting)));
    }

//    @Step("Create new event: has attendees from department/ thread/ role")
//    public void createAtteendeesEvent(String title, String type, String scheduleType, String attendees_id,
//                                      String to_department_ids, String to_role_ids, String to_thread_ids){
//        ScheduleObject schedule = new ScheduleObject();
//        schedule.setType(scheduleType);
//        List<Integer> attendees = new ArrayList<>();
//        attendees.add(Integer.parseInt(attendees_id));
//        List<String> department = new ArrayList<>();
//        department.add(to_department_ids);
//        List<String> role = new ArrayList<>();
//        role.add(to_role_ids);
//        List<String> thread = new ArrayList<>();
//        thread.add(to_thread_ids);
//
//        Map<String,Object> requestBody = new HashMap<>();
//        requestBody.put("title",title);
//        requestBody.put("type",type);
//        requestBody.put("schedule",schedule);
//        requestBody.put("attendees_id",attendees);
//        requestBody.put("to_department_ids",department);
//        requestBody.put("to_role_ids",role);
//        requestBody.put("to_thread_ids",thread);
//
//        SerenityRest.given()
//                .header("Content-Type","application/json")
//                .header("x-gapo-workspace-id","582669755189245")
//                .auth().oauth2(token)
//                .when()
//                .body(requestBody)
//                .post("https://api.gapowork.vn/calendar/v1.0/events")
//                .then().extract().response().prettyPrint();
//
//    }

    @Step("Edit event")
    public void editEvent(String title, String scheduleType, String value, String start_hour, String start_minute,
                          String end_hour, String end_minute, String identities, String method, String id) {
        List<Long> valueArray = new ArrayList<>();
        valueArray.add(Long.parseLong(value));

        ScheduleObject schedule = new ScheduleObject();
        schedule.setType(scheduleType);
        schedule.setValue(valueArray);
        schedule.setStart_hour(Integer.parseInt(start_hour));
        schedule.setEnd_hour(Integer.parseInt(end_hour));
        schedule.setStart_minute(Integer.parseInt(start_minute));
        schedule.setEnd_minute(Integer.parseInt(end_minute));

        Option options = new Option();
        List<String> identitiesArray = new ArrayList<>();
        identitiesArray.add(identities);
        options.setIdentities(identitiesArray);
        options.setMethod(method);

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("title",title);
        requestBody.put("schedule",schedule);
        requestBody.put("options",options);

        SerenityRest.given()
                .header("Content-Type","application/json")
                .pathParam("id",id)
                .auth().oauth2(token)
                .when()
                .body(requestBody)
                .patch("https://api.gapowork.vn/calendar/v1.0/events/{id}")
                .then().extract().response().prettyPrint();
    }

    @Step("Delete event")
    public void deleteEvent(String id) {
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("method","ROOT");
        SerenityRest.given()
                .header("Content-Type","application/json")
                .pathParam("id",id)
                .auth().oauth2(token)
                .when()
                .body(requestBody)
                .delete("https://api.gapowork.vn/calendar/v1.0/events/{id}");
    }
}
