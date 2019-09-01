import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem62 {

    /**
     * Data variables to store cube of numbers as per the length of the cube number
     */
    private static Set<String> threeDigit = new HashSet<>();
    private static Set<String> sixDigit = new HashSet<>();
    private static Set<String> nineDigit = new HashSet<>();
    private static Set<String> twelveDigit = new HashSet<>();

    /**
     * Method to store cubic numbers in their string format to their respective set
     */
    private static void populateCubes() {
        for (int i = 1; ; i++) {
            BigInteger bigB = new BigInteger(String.valueOf(i));
            BigInteger cube = bigB.pow(3);
            if (cube.toString().length() > 12) { break; }

            switch (cube.toString().length()) {
                case 3:
                    threeDigit.add(cube.toString());
                case 6:
                    sixDigit.add(cube.toString());
                case 9:
                    nineDigit.add(cube.toString());
                case 12:
                    twelveDigit.add(cube.toString());
            }
        }
    }

    /**
     * Method to get the respective set of cube numbers as per the length of the given cube number
     *
     * @param len
     */
    private static Set<String> getCubeSet(int len) {
        switch (len) {
            case 3:
                return threeDigit;
            case 6:
                return sixDigit;
            case 9:
                return nineDigit;
            case 12:
                return twelveDigit;
            default:
                return new HashSet<>();
        }
    }

    /**
     * Method to ascertain if the given num has exactly 5 permutations which are also cubic
     *
     * @param num
     * @param set
     */
    private static boolean fiveCubicPermutes(String num, Set<String> set) {
        int count = 0;
        for (String str : set) {
            if (isPermute(num, str)) {
                count++;
            }
        }
        return count == 5;
    }

    /**
     * Method to check if the given two numbers are permutations of each other
     *
     * @param num1
     * @param num2
     */
    private static boolean isPermute(String num1, String num2) {
        int[] charArray1 = new int[10];
        int[] charArray2 = new int[10];

        for (char ch : num1.toCharArray()) {
            charArray1[ch - '0']++;
        }
        for (char ch : num2.toCharArray()) {
            charArray2[ch - '0']++;
        }
        return Arrays.equals(charArray1, charArray2);
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        populateCubes();

        String result = "";
        for (int i = 3; ; i++) {
            BigInteger bigB = new BigInteger(String.valueOf(i));
            BigInteger cube = bigB.pow(3);

            Set<String> set = getCubeSet(cube.toString().length());
            if (set.isEmpty()) { continue; }
            if (fiveCubicPermutes(cube.toString(), set)) {
                result = cube.toString();
                break;
            }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}