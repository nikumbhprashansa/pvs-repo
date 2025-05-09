package PayloadResponseWithSerializationDeserialization;

import PayloadResponseWithSerializationDeserialization.POJO.AddClassPOJO;
import PayloadResponseWithSerializationDeserialization.POJO.AddClassResponse;
import PayloadResponseWithSerializationDeserialization.POJO.Location;
import io.restassured.RestAssured;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlace {

    public static void main(String args[]) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        AddClassPOJO acp=new AddClassPOJO();
        AddClassResponse acrp=new AddClassResponse();
        Location loc=new Location();
        loc.setLat("-38.383494");
        loc.setLng("33.427362");
        acp.setLocation(loc);
        acp.setAccuracy("50");
        acp.setName("Townlake");
        acp.setPhone_number("(+91) 983 893 3937");
        acp.setAddress("29, side layout, cohen 09");
        acp.setWebsite("http://google.com");
        acp.setLanguage("French-IN");
        List<String> types=new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        acp.setTypes(types);
        AddClassResponse  result= given().log().all().queryParam("key","qaclick123").body(acp)
                .when().post("/maps/api/place/add/json").as(AddClassResponse.class);

        System.out.println(result.getPlace_id());
        Assert.assertEquals(result.getStatus(),"OK");
    }

}
