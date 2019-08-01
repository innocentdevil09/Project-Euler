public class Problem5 {

    /**
     * Method to return the smallest positive number which is evenly divisible by all the numbers in the range 1 to num
     *
     * @param range
     */
    private static long smallestCommonMultiple(int range) {
        if (range <= 1) {
            throw new IllegalArgumentException("Illegal argument: " + range);
        }
        long multiple = 1;
        for (int i = range; i > 1; i--) {
            if (multiple % i == 0) {
                continue;
            }
            multiple = (multiple * i) / gcd(multiple, i);
        }
        return multiple;
    }

    /**
     * Method to get the greatest common divisor for the given two numbers
     *
     * @param num1
     * @param num2
     */
    private static long gcd(long num1, long num2) {
        if (num1 < 1 || num2 < 1) {
            throw new IllegalArgumentException("Illegal arguments: " + num1 + ", " + num2);
        }
        while (num2 > 0) {
            long temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    public static void main(String[] args) {
        System.out.println(smallestCommonMultiple(20));
    }
}
