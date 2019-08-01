import java.util.ArrayList;
import java.util.List;

public class Problem10 {
    /**
     * This is by far the best method i've seen to get a list of prime numbers in the range of 1 to n
     * Algo :-
     * 1. Initiate a boolean of size n + 1 to indicate prime numbers
     * 2. Mark boolean index at 2 as true
     * 3. Mark all boolean index at odd integers from 3 to n as true
     * 4. Next we identify those odd integers that are square of odd integers e.g. 9, 25, 49, 81, etc. and mark those
     * index as false since they are not prime numbers
     * <p>
     * Very accurate algorithm to compute a list of prime numbers
     *
     * @param n
     */
    private static boolean[] listOfPrimes(int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        boolean[] result = new boolean[n + 1];
        if (n >= 2) { result[2] = true; }
        for (int i = 3; i <= n; i += 2) {
            result[i] = true;
        }
        for (int j = 3; j * j <= n; j += 2) {
            if (result[j]) {
                for (int k = j * j; k < n; k += j * 2) { result[k] = false; }
            }
        }
        return result;
    }

    /**
     * Method to return a list of prime numbers below n
     *
     * @param n
     */
    private static List<Integer> arrayOfPrimes(int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        int count = 0;
        List<Integer> arr = new ArrayList<>();
        boolean[] primes = listOfPrimes(n);
        for (boolean p : primes) {
            if (p) {
                arr.add(count);
            }
            count++;
        }
        return arr;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> result = arrayOfPrimes(2000000);
        long sum = result.stream().mapToLong(i -> i).sum();
        System.out.println(sum);

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime));
    }
}
