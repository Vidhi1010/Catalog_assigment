import java.math.BigInteger;
import java.util.Map;

public class LagrangeInterpolator {

    // Method to perform Lagrange interpolation and find the constant term 'c'
    public static BigInteger interpolate(Map<Integer, BigInteger> points, int k) {
        BigInteger constantTerm = BigInteger.ZERO;

        // Iterate over each point (x, y)
        for (Map.Entry<Integer, BigInteger> entryI : points.entrySet()) {
            int xi = entryI.getKey();
            BigInteger yi = entryI.getValue();

            BigInteger term = yi;

            for (Map.Entry<Integer, BigInteger> entryJ : points.entrySet()) {
                int xj = entryJ.getKey();
                if (xi != xj) {
                    // (x - xj) / (xi - xj) when x = 0, simplifies to -xj / (xi - xj)
                    term = term.multiply(BigInteger.valueOf(-xj)).divide(BigInteger.valueOf(xi - xj));
                }
            }

            constantTerm = constantTerm.add(term);
        }

        return constantTerm;
    }
}
