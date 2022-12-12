package pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;
import java.util.Random;

import static pages.LoginActions.token;
import static pages.WorkspaceActions.wpId;

public class RoomActions {
    public static String roomId;
    @Step("Get room id")
    public void getRoomId() {
        Response res = SerenityRest.given()
                .header("x-gapo-workspace-id",wpId)
                .auth().oauth2(token)
                .when()
                .get("https://api.gapowork.vn/calendar/v1.0/room");
        List<String> ListRoomId = res.path("data._id");
        Random rd = new Random();
        int length = res.path("data.size()");
        int n = rd.nextInt(length - 1);
        roomId = res.path("data["+n+"]._id");
    }
}
