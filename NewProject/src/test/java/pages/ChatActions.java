package pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static pages.LoginActions.token;

public class ChatActions {
    @Step("Get list thread chat")
    public void getThread() {
        Response res = SerenityRest.given()
                .auth().oauth2(token)
                .when().get("https://staging-messenger.gapowork.vn/chat/v3.3");
        List<Long> ThreadId = res.path("data.id");
    }
}
