import java.math.BigInteger;

public class Problem20 {

    private static BigInteger getFactorial(int n) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            factorial = factorial.multiply(new BigInteger(String.valueOf(i)));
        }
        return factorial;
    }

    public static void main(String[] args) {
        BigInteger integer = getFactorial(100);
        String val = integer.toString();
        int result = 0, i = 0;
        while (i < val.length()) {
            result += val.charAt(i++) - '0';
        }
        System.out.println(result);
    }
}