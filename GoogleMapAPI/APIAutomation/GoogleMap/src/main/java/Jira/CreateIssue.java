package Jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;


public class CreateIssue {
    @Test
    public void createIssue()
    {
        RestAssured.baseURI="https://nikumbhprashansa.atlassian.net";
      String response= given().header("Authorization","Basic bmlrdW1iaHByYXNoYW5zYUBnbWFpbC5jb206QVRBVFQzeEZmR0YwdVlXZlN1RXJPZGUxb2pQQ1pfdlJKYUtWRGFTQl8xbW9xdjFCakJkYThkUnB6S2d1anZpOF9Yc1NwTTNDc0ZzamlUQk05SzQxQjZxaHc1MVBqUmNNcklqeWtTeWZfZTlLMEhFcTJNaHJMbEh1ZDl0aVBXd1l2T0QyRWl1eG4xaDh0T0tMY3Zja2c0MkR1UkE0NDJEMU5XTnRsdFd5T0prNkVrUFgtTEVGWVVBPTZFQkQ1Q0VG")
              .header("Content-Type","application/json")
              .body("{\n" +
                      "    \"fields\": {\n" +
                      "       \"project\":\n" +
                      "       {\n" +
                      "          \"key\": \"SCRUM\"\n" +
                      "       },\n" +
                      "       \"summary\": \"Dropdown is not working.\",\n" +
                      "       \"issuetype\": {\n" +
                      "          \"name\": \"Bug\" \n" +
                      "       }\n" +
                      "   }\n" +
                      "}").when().post("/rest/api/3/issue")
                        .then().log().all().assertThat().statusCode(201).extract().response().asString();

        JsonPath js=new JsonPath(response);
        String issueId=js.getString("id");
//How to use Path Parameter
        //multipart method for attachment
        given().pathParam("key",issueId).header("Authorization","Basic bmlrdW1iaHByYXNoYW5zYUBnbWFpbC5jb206QVRBVFQzeEZmR0YwdVlXZlN1RXJPZGUxb2pQQ1pfdlJKYUtWRGFTQl8xbW9xdjFCakJkYThkUnB6S2d1anZpOF9Yc1NwTTNDc0ZzamlUQk05SzQxQjZxaHc1MVBqUmNNcklqeWtTeWZfZTlLMEhFcTJNaHJMbEh1ZDl0aVBXd1l2T0QyRWl1eG4xaDh0T0tMY3Zja2c0MkR1UkE0NDJEMU5XTnRsdFd5T0prNkVrUFgtTEVGWVVBPTZFQkQ1Q0VG")
                .header("X-Atlassian-Token","no-check").multiPart("file", new File("C:\\Prashansa\\Study\\API Testing\\Jira.txt")).
                when().post("https://nikumbhprashansa.atlassian.net/rest/api/3/issue/{key}/attachments")
                        .then().log().all().assertThat().statusCode(200);

    }
}
