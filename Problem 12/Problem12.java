import java.util.HashSet;
import java.util.Set;

public class Problem12 {
    /**
     * Method to return triangle number for the given index n
     *
     * @param n
     */
    private static int getTriangleNumber(int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        int number = 0;
        for (int i = 0; i <= n; i++) { number += i; }
        return number;
    }

    /**
     * Method to return the number of divisors for a given number n
     *
     * @param n
     */
    private static int getDivisorsSize(int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        n = getTriangleNumber(n);

        Set<Integer> arr = new HashSet<Integer>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                int factor2 = n / i;
                if (i != factor2) {
                    arr.add(i);
                    arr.add(factor2);
                }
            }
        }
        return arr.size();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int n = 1;
        while (getDivisorsSize(n) < 500) {
            n++;
        }
        System.out.println(getTriangleNumber(n));
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken = " + (endTime - startTime) + " ms");
    }
}
