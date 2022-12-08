package pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.lang.reflect.Array;
import java.util.List;

import static pages.LoginActions.token;
import static pages.LoginActions.userId;

public class WorkspaceActions {
    public static long wpId;
    @Step("Get workspace's id")
    public void getWpID() {
        Response res = SerenityRest.given()
                .header("x-gapo-user-id",userId)
                .auth().oauth2(token)
                .when().get("https://api.gapowork.vn/workspace/v1.0/users/me");
        List<Long> ListwpId = res.path("data.user_workspace.id");
    }

    @Step("get list active member")
    public void getActiveMem() {
        Response res = SerenityRest.given()
                .header("x-gapo-workspace-id",wpId)
                .auth().oauth2(token)
                .queryParam("state",1)
                .when().get("https://api.gapowork.vn/workspace/v1.0/users");

    }
}
