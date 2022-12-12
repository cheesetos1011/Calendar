package pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static pages.LoginActions.token;
import static pages.WorkspaceActions.wpId;

public class OrgChartActions {
    @Step("Get list department")
    public void getDepartment() {
        Response res = SerenityRest.given()
                .header("x-gapo-workspace-id",wpId)
                .auth().oauth2(token)
                .when().get("https://api.gapowork.vn/organization-chart/v3.0/department");
        List<String> ListDepartment = res.path("data.id");
    }

    @Step("Get list role")
    public void getRole() {
        Response res = SerenityRest.given()
                .header("x-gapo-workspace-id",wpId)
                .auth().oauth2(token)
                .when().get("https://api.gapowork.vn/organization-chart/v3.0/role");
        List<String> ListRole = res.path("data.id");
    }
}
