public class Problem34 {

    /**
     * Memoizer to store factorials for each digit
     */
    private static int[] factorialMap = new int[10];

    /**
     * Method to check if the sum of the factorial of each digit in a number is equal to the number itself
     *
     * @param x
     */
    private static boolean isFactorialDigitSum(int x) {
        int temp = x, sum = 0;

        while (temp > 0) {
            sum += factorial(temp % 10);
            temp /= 10;
        }
        return sum == x;
    }

    /**
     * Method to return factorial value of given digit
     *
     * @param digit
     */
    private static int factorial(int digit) {
        if (factorialMap[digit] != 0) {
            return factorialMap[digit];
        }
        int factorial = 1;
        for (int i = digit; i > 0; i--) {
            factorial *= i;
        }
        factorialMap[digit] = factorial;
        return factorial;
    }

    /**
     * Main method.
     * The mathematical limit for i should be 9999999 (7 times) because any 8-digit number from 1,00,00,000 is always
     * greater than the sum of its each digit factorial => (8 * 9! = 2903040)
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int sum = 0;
        for (int i = 3; i < 50000; i++) {
            if (isFactorialDigitSum(i)) {
                System.out.println("Number: " + i);
                sum += i;
            }
        }

        System.out.println(sum);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}