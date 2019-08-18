/**
 * It seemed superflous to have the triangle numbers since all hexagonal numbers are also triangle number.
 * H = h*(2h-1)
 * T = t*(t+1)/2
 * generating t by t=2h-1,
 * T = (2h-1)(2h-1+1)/2 = (2h-1)(2h)/2 = h(2h-1),
 * To make calculation faster I ingored triangle aspect.
 * <p>
 * That is part of the process in solving these problems: stripping them down to the
 * bare essentials.
 * <p>
 * To solve this problem I ran through the hexagon numbers and, rather than compare with a separate array/list of
 * pentagon numbers, I used a test.
 * <p>
 * Given that P=n(3n-1)/2, we get the quadratic, 3n^2-n-2P=0, and using the quadratic formula,
 * n=(1+ math.sqrt(1+24P))/6
 * (only taking positive root). In other words, if the number being test, P, produces an integer in the formula, then
 * it is a pentagon number.
 */
public class Problem45 {

    /**
     * Method to check if a given num is a pentagonal number
     *
     * @param num
     */
    private static boolean isPentagonal(long num) {
        double val = (1 + Math.sqrt((24L * num) + 1)) / 6;
        return (long) val == val;
    }

    /**
     * Method to get hexagonal number for the given index
     *
     * @param n
     */
    private static long getHexagonal(int n) {
        return ((2 * n) - 1) * n;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        long result;
        int index;
        for (int i = 144; ; i++) {
            long hexagonal = getHexagonal(i);
            if (isPentagonal(hexagonal)) {
                result = hexagonal;
                index = i;
                break;
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Next triangle, pentagon & hexagon number - " + result + " found at index " + index);
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}