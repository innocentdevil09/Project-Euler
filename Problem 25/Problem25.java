import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem25 {

    /**
     * Ideally, this problem should've been solved by using an array lookup instead of list.
     * Getting an element at index i of a list is costlier than an array. The problem is how many elements to assign to
     * that array.
     *
     * One way is by computing the value : log1.6 (10 ^ 999) ~ 4900 => Initialize the array to contain 5000 values
     * But there must be a data structure in java which provides the flexibility to add values dynamically and at the
     * same time has O(1) lookup time complexity
     *
     * The below approach is 30X times slower than array approach
     * Time taken by array memoization approach : 7 ms
     * Time taken by list memoization approach  : 211 ms
     */
    private static List<BigInteger> fibonacci = new ArrayList<>();

    static {
        fibonacci.add(BigInteger.ZERO);
        fibonacci.add(BigInteger.ONE);
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int index = 2;
        while (true) {
            BigInteger fibonacciTerm = fibonacci.get(index - 1).add(fibonacci.get(index - 2));
            if (fibonacciTerm.toString().length() >= 1000) {
                break;
            }
            fibonacci.add(fibonacciTerm);
            index++;
        }
        System.out.println(index);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}