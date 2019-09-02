import java.util.ArrayList;
import java.util.List;

public class Problem64 {

    private static final int THRESHOLD = 10000;

    /**
     * From the given article :
     * https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
     * <p>
     * The following iterative algorithm[12] can be used to obtain the continued fraction expansion in canonical form
     * (S is any natural number that is not a perfect square):
     * <p>
     * m_{0} = 0
     * d_{0} = 1
     * a_{0} = (int) sqrt(S)
     * <p>
     * m_{n+1} = d_{n} * a_{n} - m_{n}
     * d_{n+1} = {\frac {S - m_{n+1}^2} / d_{n}}
     * <p>
     * Notice that mn, dn, and an are always integers. The algorithm terminates when this triplet is the same as one
     * encountered before. The algorithm can also terminate on ai when ai = 2 * a0 which is easier to implement.
     * <p>
     * The expansion will repeat from then on. The sequence [a0; a1, a2, a3, ...] is the continued fraction expansion:
     *
     * @param sqr
     */
    private static List<Integer> getPeriod(int sqr) {
        List<Integer> period = new ArrayList<>();
        double a0 = Math.sqrt(sqr);
        if (a0 == (int) a0) { return period; }

        int aInt = (int) a0, m0 = 0, d0 = 1;
        int aCache = Integer.MAX_VALUE, mCache = Integer.MAX_VALUE, dCache = Integer.MAX_VALUE;
        while (true) {
            int m = (d0 * aInt) - m0;
            int d = (sqr - (m * m)) / d0;
            int a = ((int) a0 + m) / d;

            if (a == aCache && mCache == m && dCache == d) { break; }

            period.add(a);
            if (aCache == Integer.MAX_VALUE) { aCache = a; }
            if (mCache == Integer.MAX_VALUE) { mCache = m; }
            if (dCache == Integer.MAX_VALUE) { dCache = d; }
            m0 = m;
            d0 = d;
            aInt = a;
        }
        return period;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int count = 0;

        for (int i = 2; i <= THRESHOLD; i++) {
            List<Integer> period = getPeriod(i);
            if (period.size() % 2 == 1) { count++; }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
