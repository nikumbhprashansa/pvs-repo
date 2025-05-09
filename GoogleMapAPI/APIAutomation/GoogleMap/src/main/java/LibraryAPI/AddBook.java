package LibraryAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddBook {


    public void addBook()
    {
        RestAssured.baseURI="http://216.10.245.166";
    String response=    given().body("{\n" +
                "    \"name\": \"Learn Appium Automation with Java\",\n" +
                "    \"isbn\": \"bcd\",\n" +
                "    \"aisle\": \"227\",\n" +
                "    \"author\": \"John foe\"\n" +
                "}")
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

     JsonPath js=new JsonPath(response);
    String actualResult= js.getString("Msg");
        Assert.assertEquals(actualResult,"successfully added");
    }

    /*Payload creation ways:
    1. dynamically build json and send data externally
    2. Use Data Provider
    3. Send Json file into post method; Content of file is converted to Bytes-->Content of Bytes converted to String
     */

    //1. dynamically build json and send data externally
    @Test
    public void addBookDynamically()
    {
        RestAssured.baseURI="http://216.10.245.166";
        String response=    given().body(LibraryPayload.addBookPayload("PRN","112"))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=new JsonPath(response);
        String actualResult= js.getString("Msg");
        Assert.assertEquals(actualResult,"successfully added");
    }


// Use Data Provider to Add data
   // @Test(dataProvider="Data")
    public void addBookWithDataProvider(String str1,String str2)
    {
        RestAssured.baseURI="http://216.10.245.166";
        String response=    given().body(LibraryPayload.addBookPayload(str1,str2))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=new JsonPath(response);
        String actualResult= js.getString("Msg");
        Assert.assertEquals(actualResult,"successfully added");
    }

    @DataProvider(name="Data")
    public Object[][] getData()
    {
     return  new Object[][] {{"PRN","113"},{"PRN","114"},{"PRN","115"}};
    }

    //Send Json file into post method File with .Json extension-->Content of file is converted to Bytes-->Content of Bytes converted to String
    @Test
    public void addBookWithJsonFile() throws IOException {
        String str=new String(Files.readAllBytes(Paths.get("C:\\Prashansa\\Study\\API Testing\\GoogleMapAPI\\APIAutomation\\GoogleMap\\src\\main\\java\\LibraryAPI\\Data.Json")));
        RestAssured.baseURI="http://216.10.245.166";
        String response=    given().body(str)
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=new JsonPath(response);
        String actualResult= js.getString("Msg");
    }



}
