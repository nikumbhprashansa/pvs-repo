package LibraryAPI;

public class LibraryPayload {

    public static String addBookPayload(String isbn, String aisle){

    String response = "{\n" +
            "    \"name\": \"Learn Appium Automation with Java\",\n" +
            "    \"isbn\": \""+isbn+"\",\n" +
            "    \"aisle\": \""+aisle+"\",\n" +
            "    \"author\": \"John foe\"\n" +
            "}";
    return response;
}
}
