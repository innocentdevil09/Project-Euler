import java.math.BigInteger;

public class Problem16 {

    public static void main(String[] args) {
        BigInteger val = BigInteger.ONE;
        for (int i = 1; i <= 1000; i++) {
            val = val.multiply(new BigInteger("2"));
        }

        String s = val.toString();
        int result = 0, i = 0;
        while (i < s.length()) {
            result += s.charAt(i++) - '0';
        }
        System.out.println(result);
    }
}
