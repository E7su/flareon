package my.Objects;

import org.json.JSONObject;
import my.ParsersAndHelpers.ParserHelper;

/**
 * Created by e7su on 02.06.17.
 */
public class TestJSONParser {
    public static void main(String[] args) {

        String input_string = "{\"data\":{\"message_category\":\"client\",\"client\":\"linux\"}}";

        JSONObject input_json_object = new JSONObject(input_string);
        JSONObject data = input_json_object.getJSONObject("data");
        JSONObject good_headers = new JSONObject();
        JSONObject bad_headers = new JSONObject();

        System.out.println(data);
        try {
            good_headers = input_json_object.getJSONObject("data");
            bad_headers = input_json_object.getJSONObject("no_data");
        } catch (Exception e) {
            System.out.println();
        }
        String test = ParserHelper.getStringOrNull(good_headers, "client");
        System.out.println(test);
        test = ParserHelper.getStringOrNull(bad_headers, "client");
        System.out.println(test);
    }
}
