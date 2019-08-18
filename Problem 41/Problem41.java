import java.util.HashSet;
import java.util.Set;

public class Problem41 {

    /**
     * The value of MAX is limited to 7 digits because any number:
     * 1. that is 9 digit : (1+2+3+4+5+6+7+8+9) = 45 is divisible by 3
     * 2. that is 8 digit : (1+2+3+4+5+6+7+8) = 36 is divisible by 3
     * <p>
     * ALSO,
     * 1. that is 6 digit : (1+2+3+4+5+6) = 21 is divisible by 3
     * 2. that is 5 digit : (1+2+3+4+5) = 15 is divisible by 3
     * 3. that is 3 digit : (1+2+3) = 6 is divisible by 3
     */
    private static final int MAX = 10000000;
    private static boolean[] primes = new boolean[MAX];

    static {
        listOfPrimes();
    }

    private static void listOfPrimes() {
        primes[2] = true;
        for (int i = 3; i < MAX; i += 2) {
            primes[i] = true;
        }
        for (int k = 3; k * k <= MAX; k += 2) {
            if (primes[k]) {
                for (int j = k * k; j < MAX; j += 2 * k) {
                    primes[j] = false;
                }
            }
        }
    }

    /**
     * Method to check if a given number is pan-digital i.e. it contains all the digits from 1 to n where n is the
     * number of digits e.g. for n = 3 => 231; for n = 4 => 2413
     *
     * @param num
     * @param digits
     */
    private static boolean isPandigital(String num, int digits) {
        if (num.length() != digits) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(i) - '0';
            if (val == 0 || val > digits) {
                continue;
            }
            set.add(val);
        }
        return set.size() == digits;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int largest = -1;
        for (int i = 2; i < MAX; i++) {
            String num = String.valueOf(i);
            if (num.length() == 6 || num.length() == 5 || num.length() == 3) {
                i = (int) Math.pow(10, num.length());
                continue;
            }
            if (primes[i] && isPandigital(num, num.length())) {
                largest = i;
            }
        }
        System.out.println(largest);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
