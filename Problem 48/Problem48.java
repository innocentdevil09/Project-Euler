import java.math.BigInteger;

public class Problem48 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= 1000; i++) {
            BigInteger val = new BigInteger(String.valueOf(i));
            val = val.pow(i);
            sum = sum.add(val);
        }
        String result = sum.toString();
        int index = result.length() - 10;
        long endTime = System.currentTimeMillis();

        System.out.println("Result - " + result.substring(index));
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}
