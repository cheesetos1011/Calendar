package pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.lang.reflect.Array;
import java.util.List;

import static pages.LoginActions.token;
import static pages.LoginActions.userId;

public class WorkspaceActions {
    public static String wpId;
    @Step("Get workspace's id")
    public void getWpID() {
        Response res = SerenityRest.given()
                .header("x-gapo-user-id",userId)
                .auth().oauth2(token)
                .when().get("https://api.gapowork.vn/workspace/v1.0/users/me");
        List<String> ListwpId = res.path("data.user_workspace.id");
        wpId = ListwpId.get(0);
    }

    @Step("get list active member")
    public void getActiveMem() {
        Response res = SerenityRest.given()
                .header("x-gapo-workspace-id",wpId)
                .auth().oauth2(token)
                .queryParam("state",1)
                .when().get("https://api.gapowork.vn/workspace/v1.0/users");
        List<Long> listActiveMem = res.path("data.user.id");
    }
}
