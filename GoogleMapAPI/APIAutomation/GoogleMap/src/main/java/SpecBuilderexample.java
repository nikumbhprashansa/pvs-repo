import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class SpecBuilderexample {
    @Test()
    public void addPlace()
    {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RequestSpecification request=requestSpecBuilder.setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","qaclick123").build();

        ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder();
        ResponseSpecification response= responseSpecBuilder.expectContentType("application/json").expectStatusCode(200).build();

        baseURI="https://rahulshettyacademy.com";
        given().spec(request).body("{\n" +
                        "    \"location\": {\n" +
                        "        \"lat\": -38.383494,\n" +
                        "        \"lng\": 33.427362\n" +
                        "    },\n" +
                        "    \"accuracy\": 50,\n" +
                        "    \"name\": \"Frontline house\",\n" +
                        "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "    \"address\": \"29, side layout, cohen 09\",\n" +
                        "    \"types\": [\n" +
                        "        \"shoe park\",\n" +
                        "        \"shop\"\n" +
                        "    ],\n" +
                        "    \"website\": \"http://google.com\",\n" +
                        "    \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then().spec(response);

    }

}
