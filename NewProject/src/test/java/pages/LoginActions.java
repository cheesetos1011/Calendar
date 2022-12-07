package pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class LoginActions {
    public static String token;

    @Step("Check email if exist")
    public String checkEmail(String email) {
        Map<String,String> requestBody = new HashMap<>();
        requestBody.put("email",email);

        Response res =  SerenityRest
                .given().header("Content-Type","application/json")
                .when().body(requestBody).post("https://api.gapowork.vn/auth/v3.0/check-email");

        return res.path("data.salt");
    }

//    public String getUserID(String email) {
//        return checkEmail().path("data.user_id");
//    }

    @Step("Login")
    public void checkLogin(String email, String pass) {
        String salt = checkEmail(email);

        String passSHA = DigestUtils.sha256Hex((DigestUtils.sha256Hex(pass+salt))+salt);

        Map<String,Object> requestBodyLogin = new HashMap<>();
        requestBodyLogin.put("device_id","2b6e1dbd-861e-49e7-9c1d-66452d4e6be8");
        requestBodyLogin.put("email",email);
        requestBodyLogin.put("password",passSHA);
        requestBodyLogin.put("client_id","cuxlp0ugglm3krp1ab81");
        requestBodyLogin.put("trusted_device",false);

        Response res = SerenityRest.given()
                .header("Content-Type","application/json")
                .when()
                    .body(requestBodyLogin)
                    .post("https://api.gapowork.vn/auth/v3.0/login");
        token = res.path("data.access_token");
    }
}
