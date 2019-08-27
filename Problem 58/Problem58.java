import java.util.ArrayList;
import java.util.List;

public class Problem58 {

    /**
     * Method to determine if a given number is a prime number
     *
     * @param n
     */
    private static boolean isPrime(long n) {
        if (n <= 1) { return false; }
        if (n == 2L) {
            return true;
        }

        for (long i = 2L; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Residual code : NOT IN USE
     * <p>
     * Method to get diagonal numbers of a spiral matrix
     *
     * 37 36 35 34 33 32 31
     * 38 17 16 15 14 13 30
     * 39 18  5  4  3 12 29
     * 40 19  6  1  2 11 28
     * 41 20  7  8  9 10 27
     * 42 21 22 23 24 25 26
     * 43 44 45 46 47 48 49
     *
     * @param size
     */
    private static List<Long> getSpiralMatrixDiagonals(int size) {
        if (size < 1 || size % 2 == 0) { throw new IllegalArgumentException(); }
        List<Long> diagonals = new ArrayList<>();
        if (size == 1) {
            diagonals.add(1L);
            return diagonals;
        }
        long diff1 = 2L, diff2 = 4L, diff3 = 6L, diff4 = 8L;
        long num1 = 1L, num2 = 1L, num3 = 1L, num4 = 1L;

        for (int i = 3; i <= size; i += 2) {
            num1 += diff1;
            num2 += diff2;
            num3 += diff3;
            num4 += diff4;

            diagonals.add(num1);
            diagonals.add(num2);
            diagonals.add(num3);
            diagonals.add(num4);

            diff1 += 8;
            diff2 += 8;
            diff3 += 8;
            diff4 += 8;
        }
        return diagonals;
    }

    /**
     * Main method to execute the code
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int diff1 = 2, diff2 = 4, diff3 = 6, diff4 = 8;
        int num1 = 1, num2 = 1, num3 = 1, num4 = 1;

        int countOfPrimes = 0;
        int result;
        for (result = 3; ; result += 2) {
            num1 += diff1;
            num2 += diff2;
            num3 += diff3;
            num4 += diff4;

            if (isPrime(num1)) { countOfPrimes++; }
            if (isPrime(num2)) { countOfPrimes++; }
            if (isPrime(num3)) { countOfPrimes++; }
            if (isPrime(num4)) { countOfPrimes++; }

            diff1 += 8;
            diff2 += 8;
            diff3 += 8;
            diff4 += 8;

            double ratio = (double) countOfPrimes / ((2 * result) - 1);
            System.out.println("Ratio : " + ratio + " for size " + result);
            if (ratio < 0.1) { break; }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}