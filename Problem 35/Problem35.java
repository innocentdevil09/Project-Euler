public class Problem35 {

    private static final int MILLION = 1000000;
    private static boolean[] primes;

    static {
        listOfPrimes(MILLION);
    }

    /**
     * Method to populate a list of primes under one million at the start of the application
     *
     * @param n
     */
    private static void listOfPrimes(int n) {
        primes = new boolean[n + 1];
        primes[2] = true;

        for (int i = 3; i <= n; i += 2) {
            primes[i] = true;
        }
        for (int k = 3; k * k <= n; k += 2) {
            if (primes[k]) {
                for (int j = k * k; j <= n; j += 2 * k) {
                    primes[j] = false;
                }
            }
        }
    }

    /**
     * Method to determine if the all the rotations of the given number is a prime number
     *
     * @param num
     */
    private static boolean isCircularPrime(int num) {
        if (!primes[num]) {
            return false;
        }

        String origVal = String.valueOf(num);

        for (int i = 1; i < origVal.length(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(origVal.charAt(i));

            int temp = (i + 1) % origVal.length();
            while (i != temp) {
                sb.append(origVal.charAt(temp));
                temp = (temp + 1) % origVal.length();
            }

            int newVal = Integer.parseInt(sb.toString());
            if (!primes[newVal]) { return false; }
        }
        return true;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int count = 0;

        for (int i = 2; i < MILLION; i++) {
            if (isCircularPrime(i)) {
                System.out.println("Number : " + i);
                count++;
            }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
