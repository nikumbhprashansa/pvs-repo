package Authorisation;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GrantTypeClientCredential {
    String token=null;
 @BeforeTest
    public String generateToken()
    {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type","client_credentials")
                .when().post("/oauthapi/oauth2/resourceOwner/token")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js= new JsonPath(response);
        token=js.getString("access_token");
        return token;
    }

    @Test
    public void getCourseDetailsWithToken()
    {
        System.out.println("token is "+token);
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response =given().queryParam("access_token",token)
                .when().get("/oauthapi/getCourseDetails")
                .then().log().all().extract().response().asString();
        JsonPath js= new JsonPath(response);
       System.out.println("courses are "+js.getString("courses.webAutomation[1].price"));
    }

}
