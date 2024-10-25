import java.math.BigInteger;

public class Decoder {

    // Method to decode the value from the provided base to base-10
    public static BigInteger decodeValue(int base, String value) {
        return new BigInteger(value, base);
    }
}
