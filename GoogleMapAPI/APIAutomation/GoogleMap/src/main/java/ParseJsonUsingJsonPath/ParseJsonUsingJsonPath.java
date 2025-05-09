package ParseJsonUsingJsonPath;
import io.restassured.path.json.JsonPath;

public class ParseJsonUsingJsonPath {

    public static void main(String[] args) {
      // character { shows element in Json
      //  character [] shows array

        JsonPath js=new JsonPath(JsonFile.response);

       //here instructor, courses are elements
         System.out.println("instructor  is"+ js.getString("instructor"));
        System.out.println("courses  is"+ js.getString("courses"));

//webAutomation starts with [ so it is array
        System.out.println("Price of 2nd webautomation course  is"+ js.getString("courses.webAutomation[2].price"));

    }
}
