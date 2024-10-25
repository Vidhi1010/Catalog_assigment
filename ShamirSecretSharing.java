import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class ShamirSecretSharing {

    public static void main(String[] args) {
        try {
            // Parse the JSON file
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("testcase.json"));

            // Get the keys (n and k)
            JSONObject keysObject = (JSONObject) jsonObject.get("keys");
            int n = ((Long) keysObject.get("n")).intValue();
            int k = ((Long) keysObject.get("k")).intValue();

            // Store the points (x, y) after decoding
            Map<Integer, BigInteger> points = new HashMap<>();

            // Decode the y values for each x
            for (Object key : jsonObject.keySet()) {
                if (!key.equals("keys")) {
                    String keyStr = (String) key;
                    int x = Integer.parseInt(keyStr); // x is the key in JSON

                    JSONObject valueObject = (JSONObject) jsonObject.get(keyStr);
                    int base = Integer.parseInt((String) valueObject.get("base")); // Base of the y value
                    String value = (String) valueObject.get("value"); // Encoded y value

                    // Decode the y value
                    BigInteger y = Decoder.decodeValue(base, value);

                    // Store the (x, y) pair
                    points.put(x, y);
                }
            }

            // Perform Lagrange interpolation and find the constant term 'c'
            BigInteger constantTerm = LagrangeInterpolator.interpolate(points, k);

            // Output the result
            System.out.println("The secret constant term 'c' is: " + constantTerm);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
