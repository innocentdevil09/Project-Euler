import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Problem29 {
    public static void main(String[] args) {
        Set<BigInteger> allDigits = new HashSet<>();
        for (int a = 2; a <= 100; a++) {
            BigInteger bigA = new BigInteger(String.valueOf(a));
            for (int b = 2; b <= 100; b++) {
                BigInteger digit = bigA.pow(b);
                allDigits.add(digit);
            }
        }
        System.out.println("Total terms in the sequence - " + allDigits.size());
    }
}