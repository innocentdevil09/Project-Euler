import java.math.BigInteger;

public class Problem72 {

    /**
     * In number theory, Euler's totient function counts the positive integers up to a given integer n that are
     * relatively prime to n. It is written using the Greek letter phi as φ(n) or ϕ(n), and may also be called Euler's
     * phi function. In other words, it is the number of integers k in the range 1 ≤ k ≤ n for which the greatest common
     * divisor gcd(n, k) is equal to 1.
     * <p>
     * Algo :-
     * If n has the factor 2 every 2nd number will not be relatively prime to n
     * if n has the factor 3 every 3rd number will not be relatively prime to n
     * and so on....
     *
     * @param limit
     */
    private static int[] getTotients(int limit) {
        int[] totients = new int[limit + 1];
        for (int i = 2; i <= limit; i++) {
            totients[i] = i;
        }

        for (int i = 2; i <= limit; i++) {
            if (totients[i] == i) { // i is prime
                for (int j = i; j <= limit; j += i) {
                    totients[j] -= totients[j] / i;
                }
            }
        }
        return totients;
    }

    /**
     * Main method to get the count of all the fractions that occur for denominator below one million in its lowest form
     * The fraction is in its lowest form if gcd(numerator, denominator) == 1
     * Hence, the given count of totient function provides the count of co-prime numbers below a given number n. A
     * total of all totient numbers below one million, gives the total count of the fractions
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int[] totients = getTotients(1000000);
        BigInteger count = BigInteger.ZERO;
        for (int totient : totients) {
            count = count.add(BigInteger.valueOf(totient));
        }
        System.out.println(count.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
