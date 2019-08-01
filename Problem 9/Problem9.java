public class Problem9 {

    /**
     * Method to determine if the given three params satisfy pythagorean relationship
     *
     * @param a
     * @param b
     * @param c
     */
    private static boolean isPythagorean(int a, int b, int c) {
        return (a * a) + (b * b) == (c * c);
    }

    /**
     * Main method to get the values of numbers which satisfy the given two conditions :-
     * a + b + c = 1000
     * a^2 + b^2 = c^2
     *
     * @param args
     */
    public static void main(String[] args) {
        int a = 1, b = 1, c = 1;
        for (a = 1; a < 1000; a++) {
            for (b = (a + 1); b < 1000; b++) {
                c = 1000 - a - b;
                if (c < b) { break; }
                if (isPythagorean(a, b, c)) { break; }
            }
            if (isPythagorean(a, b, c)) { break; }
        }
        System.out.println("a = " + a + ", b = " + b + ", c = " + c);
        System.out.println(a * b * c);
    }
}
