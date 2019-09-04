import java.util.HashSet;
import java.util.Set;

public class Problem73 {

    /**
     * Method to get all the numbers that are co-prime to the given num
     * Algo :-
     *      * If n has the factor 2 every 2nd number will not be relatively prime to n
     *      * if n has the factor 3 every 3rd number will not be relatively prime to n
     *      * and so on....
     *
     * @param num
     */
    private static Set<Integer> getRelativePrimes(int num) {
        Set<Integer> rPrimes = new HashSet<>();
        for (int i = 1; i < num; i++) {
            rPrimes.add(i);
        }
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                for (int j = i; j < num; j += i) {
                    rPrimes.remove(j);
                }
            }
        }
        return rPrimes;
    }

    /**
     * Main method to get the count of ratios between 1/3 and 1/2
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int count = 0;
        for (int d = 1; d <= 12000; d++) {
            Set<Integer> rPrimes = getRelativePrimes(d);

            for (int rPrime : rPrimes) {
                double ratio = (double) rPrime / d;
                if (ratio > ((double) 1 / 3) && ratio < 0.5d) {
                    count++;
                }
            }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
