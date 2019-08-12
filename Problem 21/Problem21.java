import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem21 {

    /**
     * Method to get sum of all divisors of a given number. It does not include the number itself as the number
     * itself is also a divisor
     *
     * @param n
     */
    private static int getDivisorsSum(int n) {
        if (n < 1) { throw new IllegalArgumentException("Require input greater than one"); }
        Set<Integer> divisors = new HashSet<>();
        divisors.add(1);

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                divisors.add(n / i);
            }
        }
        return divisors.stream().mapToInt(d -> d).sum();
    }

    /**
     * Let d(a) = getDivisorsSum(a)
     * Method to get amicable pairs for which :-
     * 1. d(a) == b && d(b) == a
     * 2. a != b
     *
     * @param n
     */
    private static Set<Integer> getAmicablePairs(int n) {
        Set<Integer> amicablePairs = new HashSet<>();

        Map<Integer, Integer> divisorSumMap = new HashMap<>();
        for (int i = 1; i < n; i++) {
            divisorSumMap.put(i, getDivisorsSum(i));
        }
        for (int num : divisorSumMap.keySet()) {
            int divisorSum = divisorSumMap.get(num);
            if (divisorSumMap.containsKey(divisorSum) && num == divisorSumMap.get(divisorSum) && num != divisorSum) {
                amicablePairs.add(num);
                amicablePairs.add(divisorSum);
            }
        }
        return amicablePairs;
    }

    public static void main(String[] args) {
        Set<Integer> amicablePairs = getAmicablePairs(10000);
        int sum = amicablePairs.stream().mapToInt(a -> a).sum();
        System.out.println(sum);
    }
}