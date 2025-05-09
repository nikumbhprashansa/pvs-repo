import io.restassured.path.json.JsonPath;

public class ParsingJsonData {
    public static void main (String[] args) {
        String response = "{ \n" +
                "  \"accounting\" : [   \n" +
                "                     { \"firstName\" : \"John\",  \n" +
                "                       \"lastName\"  : \"Doe\",\n" +
                "                       \"age\"       : 23 },\n" +
                "\n" +
                "                     { \"firstName\" : \"Mary\",  \n" +
                "                       \"lastName\"  : \"Smith\",\n" +
                "                        \"age\"      : 32 }\n" +
                "                 ],                            \n" +
                "  \"sales\"      : [ \n" +
                "                     { \"firstName\" : \"Sally\", \n" +
                "                       \"lastName\"  : \"Green\",\n" +
                "                        \"age\"      : 27 },\n" +
                "\n" +
                "                     { \"firstName\" : \"Jim\",   \n" +
                "                       \"lastName\"  : \"Galley\",\n" +
                "                       \"age\"       : 41 }\n" +
                "                 ] \n" +
                "} \n";

        JsonPath js = new JsonPath(response);
      System.out.print("First ACccounting details: "+js.getString("accounting[0]"));
        System.out.print("2nd sales lastName: "+js.getString("sales[1].lastName"));


    }
}
