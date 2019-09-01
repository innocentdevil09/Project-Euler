import java.util.ArrayList;
import java.util.List;

public class Problem60 {

    /**
     * Class variables to define the limit of extending the threshold upto which we could find all relevant pairs
     */
    private static final int THRESHOLD = 10000;
    private static final int HUNDRED_MILLION = 100000000;
    private static boolean[] primes = new boolean[HUNDRED_MILLION + 1];

    /**
     * Method to compute all the primes below the threshold limit and save them in boolean array
     */
    private static void listOfPrimes() {
        primes[2] = true;
        for (int i = 3; i <= HUNDRED_MILLION; i += 2) {
            primes[i] = true;
        }
        for (int k = 3; k * k <= HUNDRED_MILLION; k += 2) {
            if (primes[k]) {
                for (int j = k * k; j <= HUNDRED_MILLION; j += 2 * k) {
                    primes[j] = false;
                }
            }
        }
    }

    /**
     * Method to find the lowest sum for a set of five primes for which any two primes concatenate to produce another
     * prime
     * <p>
     * The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes and concatenating them in any order
     * the result will always be prime. For example, taking 7 and 109, both 7109 and 1097 are prime. The sum of these
     * four primes, 792, represents the lowest sum for a set of four primes with this property
     * <p>
     * The current algo gets all the pair of numbers that forms concat-primes with the list of number got before that.
     * A nested 5 for loops are used to get the result of 5 primes with the minimum sum. There are many ways to
     * optimize the current code, however, they then lose their generic quality.
     */
    private static List<Integer> getConcatenatingPrimes() {
        List<Integer> result = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        int i = 3;
        List<Integer> aPairs = getAllPairs(i, new ArrayList<>());

        for (int aNum : aPairs) {
            List<Integer> aList = new ArrayList<>();
            aList.add(aNum);
            List<Integer> bPairs = getAllPairs(aNum + 2, aList);
            if (bPairs.isEmpty()) { continue; }

            for (int bNum : bPairs) {
                List<Integer> bList = new ArrayList<>(aList);
                bList.add(bNum);
                List<Integer> cPairs = getAllPairs(bNum + 2, bList);
                if (cPairs.isEmpty()) { continue; }

                for (int cNum : cPairs) {
                    List<Integer> cList = new ArrayList<>(bList);
                    cList.add(cNum);
                    List<Integer> dPairs = getAllPairs(cNum + 2, cList);
                    if (dPairs.isEmpty()) { continue; }

                    for (int dNum : dPairs) {
                        List<Integer> dList = new ArrayList<>(cList);
                        dList.add(dNum);
                        List<Integer> ePairs = getAllPairs(dNum + 2, dList);
                        if (ePairs.isEmpty()) { continue; }

                        if (aNum + bNum + cNum + dNum + ePairs.get(0) < min) {
                            min = aNum + bNum + cNum + dNum + ePairs.get(0);
                            result.clear();
                            result.addAll(dList);
                            result.add(ePairs.get(0));
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Method to check if a current number k forms a concat-prime number with all the numbers in the list of integers
     * concat-prime number is: String(k + num) && String(num + k) is a prime number
     *
     * @param result
     * @param k
     */
    private static boolean isConcatenatingPrime(List<Integer> result, int k) {
        for (int number : result) {
            int num1 = Integer.parseInt("" + k + number);
            int num2 = Integer.parseInt("" + number + k);
            if (!primes[num1] || !primes[num2]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to get all the pairs of number in a list that forms concat-prime with the numbers given in the pairs list
     *
     * @param n
     * @param pairs
     */
    private static List<Integer> getAllPairs(int n, List<Integer> pairs) {
        List<Integer> newPairs = new ArrayList<>();

        for (int i = n; i < THRESHOLD; i += 2) {
            if (primes[i] && isConcatenatingPrime(pairs, i)) {
                newPairs.add(i);
            }
        }
        return newPairs;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        listOfPrimes();
        List<Integer> fiveConcatenatingPrimes = getConcatenatingPrimes();
        int sum = fiveConcatenatingPrimes.stream().mapToInt(d -> d).sum();
        System.out.println(sum);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}