import java.math.BigInteger;

public class Problem65 {

    /**
     * Main method
     * Essentially numerator follows the pattern :
     * n_{k} = index * n_{k-1} + n_{k-2}, where index == (i % 3 == 0) ? 2 * (i / 3) : 1
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        BigInteger d = BigInteger.ONE;
        BigInteger n = BigInteger.valueOf(2L);

        for (int i = 2; i <= 100; i++) {
            int index = (i % 3 == 0) ? 2 * (i / 3) : 1;

            BigInteger temp = new BigInteger(d.toString());
            d = n;
            n = d.multiply(BigInteger.valueOf(index)).add(temp);
        }

        int sum = 0;
        for (char ch : n.toString().toCharArray()) {
            sum += ch - '0';
        }
        System.out.println(sum);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}