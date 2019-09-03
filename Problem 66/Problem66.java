import java.math.BigInteger;

public class Problem66 {

    /**
     * Class variables to define limit to the value of D in the equation: x^2 – Dy^2 = 1
     */
    private static final int LIMIT = 1000;
    private static boolean[] squares = new boolean[LIMIT + 1];

    /**
     * Method to get all squares below the LIMIT value
     */
    private static void populateSquares() {
        for (int i = 1; ; i++) {
            int sqr = i * i;
            if (sqr > LIMIT) { break; }
            squares[sqr] = true;
        }
    }

    /**
     * Method to get the minimal solution for the given equation: x^2 – Dy^2 = 1
     * <p>
     * Diophantine equations are of different forms, out of which equations of the form [x^2 – Dy^2 = 1] are called
     * Pell's equation
     * https://en.wikipedia.org/wiki/Pell%27s_equation
     * <p>
     * From the given article :
     * Pell's equation (also called the Pell–Fermat equation) is any Diophantine equation of the form
     * <p>
     * x^{2}-ny^{2} = 1
     * where n is a given positive nonsquare integer and integer solutions are sought for x and y. Joseph
     * Louis Lagrange proved that, as long as n is not a perfect square, Pell's equation has infinitely many distinct
     * integer solutions. These solutions may be used to accurately approximate the square root of n by rational
     * numbers of the form x/y.
     * <p>
     * Thus, this problems gets reduced to finding continued fraction of Math.sqrt(n) in the above equation.
     * https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
     * https://en.wikipedia.org/wiki/Continued_fraction#Infinite_continued_fractions_and_convergents
     * <p>
     * The following iterative algorithm can be used to obtain the continued fraction expansion in canonical form
     * * (S is any natural number that is not a perfect square):
     * * <p>
     * * m_{0} = 0
     * * d_{0} = 1
     * * a_{0} = (int) sqrt(S)
     * * <p>
     * * m_{n+1} = d_{n} * a_{n} - m_{n}
     * * d_{n+1} = {\frac {S - m_{n+1}^2} / d_{n}}
     * * <p>
     * If successive convergents are found, with numerators h1, h2, ... and denominators k1, k2, ... then the
     * relevant recursive relation is:
     * h_{n} = a_{n}*h_{n-1} + h_{n−2},
     * k_{n} = a_{n}*k_{n-1} + k_{n−2},
     *
     * @param sqr
     */
    private static BigInteger getMinimalSoln(int sqr) {
        BigInteger a0 = BigInteger.valueOf((int) Math.sqrt(sqr));
        BigInteger m0 = BigInteger.ZERO;
        BigInteger d0 = BigInteger.ONE;

        BigInteger aTemp = new BigInteger(a0.toString());

        BigInteger h_nMinusTwo = BigInteger.ZERO;
        BigInteger h_nMinusOne = BigInteger.ONE;

        BigInteger k_nMinusTwo = BigInteger.ONE;
        BigInteger k_nMinusOne = BigInteger.ZERO;

        BigInteger h = a0.multiply(h_nMinusOne).add(h_nMinusTwo);
        BigInteger k = a0.multiply(k_nMinusOne).add(k_nMinusTwo);

        h_nMinusTwo = h_nMinusOne;
        h_nMinusOne = h;

        k_nMinusTwo = k_nMinusOne;
        k_nMinusOne = k;

        while (true) {
            BigInteger m = aTemp.multiply(d0).subtract(m0);
            BigInteger d = BigInteger.valueOf(sqr).subtract(m.pow(2)).divide(d0);
            BigInteger a = a0.add(m).divide(d);

            h = a.multiply(h_nMinusOne).add(h_nMinusTwo);
            k = a.multiply(k_nMinusOne).add(k_nMinusTwo);

            if ((h.pow(2)).subtract(BigInteger.valueOf(sqr).multiply(k.pow(2))).equals(BigInteger.ONE)) { break; }
            m0 = m;
            d0 = d;
            aTemp = a;

            h_nMinusTwo = h_nMinusOne;
            h_nMinusOne = h;

            k_nMinusTwo = k_nMinusOne;
            k_nMinusOne = k;
        }
        return h;
    }

    /**
     * Main method to execute solution to Pell's equation
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        populateSquares();
        BigInteger maxX = BigInteger.ZERO;
        int maxD = 0;

        for (int d = 2; d <= LIMIT; d++) {
            if (squares[d]) {
                continue;
            }
            BigInteger x = getMinimalSoln(d);
            System.out.println("D : " + d + ", x = " + x);
            if (x.max(maxX).equals(x)) {
                maxX = x;
                maxD = d;
            }
        }
        System.out.println(maxD);
        long endTime = System.currentTimeMillis();
        System.out.println("Time Taken : " + (endTime - startTime));
    }
}