public class Problem3 {

    /**
     * Method to check if a given number is a prime number
     *
     * @param num
     */
    private static boolean isPrimeNumber(long num) {
        if (num <= 1) { return false; }
        if (num == 2) { return true; }

        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return the largest prime factor for a given number
     *
     * @param num
     */
    private static long largestPrimeFactor(long num) {
        if (num <= 1) {
            throw new IllegalArgumentException("Illegal argument: " + num);
        }
        for (long i = 2; i < num; i++) {
            if (num % i == 0) {
                long otherFactor = num / i;
                if (isPrimeNumber(otherFactor)) {
                    return otherFactor;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(largestPrimeFactor(600851475143L));
    }
}
