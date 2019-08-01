public class Problem2 {

    /**
     * Method to return sum of all even numbers in the fibonacci sequence which are less than the given number
     *
     * @param num
     */
    private static long fibonacciSum(long num) {
        if (num < 0) {
            throw new IllegalArgumentException("Illegal argument: " + num);
        }
        long fib0 = 0L, fib1 = 1L;
        long sum = 0, fibonacci = 0L;

        while (fibonacci < num) {
            fibonacci = fib0 + fib1;
            fib0 = fib1;
            fib1 = fibonacci;

            if (fibonacci % 2 == 0) {
                sum += fibonacci;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(fibonacciSum(4000000));
    }
}
