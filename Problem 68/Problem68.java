import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem68 {

    /**
     * Method to get all the pairs of numbers that satisfy the magic 5-gon conditions
     * Algo :-
     * 1. Create an array for all the elements in the external gons
     * 2. Also, an array for all the sides
     * 3. Since we are looking for only 16 digit strings, number 10 cannot be a part of the sides as then it will
     * occur twice in the calculation
     * 4. Also, as we are looking for the maximum of the 16 digit string, all the external gons contains the maximum
     * elements from the range(1, 10)
     * 5. Define a permutation method to get permutations of the array, and iterate over all the permutations of both
     * externals and sides
     */
    private static List<String> getPairs() {
        List<String> list = new ArrayList<>();
        int[] exts = {6, 7, 8, 9, 10};
        int[] sides = {1, 2, 3, 4, 5};

        boolean isExtPemutesFinished = false;
        boolean isSidesPemutesFinished = false;
        while (!isExtPemutesFinished) {
            while (!isSidesPemutesFinished) {

                boolean isPair = true;
                int num = exts[0] + sides[0] + sides[1];
                if (exts[1] + sides[1] + sides[2] != num) { isPair = false; }
                if (exts[2] + sides[2] + sides[3] != num) { isPair = false; }
                if (exts[3] + sides[3] + sides[4] != num) { isPair = false; }
                if (exts[4] + sides[4] + sides[0] != num) { isPair = false; }

                if (isPair) {
                    list.add(getString(exts, sides));
                }
                isSidesPemutesFinished = nextPermutation(sides);
            }
            sides = new int[]{1, 2, 3, 4, 5};
            isSidesPemutesFinished = false;
            isExtPemutesFinished = nextPermutation(exts);
        }
        return list;
    }

    /**
     * Method to swap elements in the given array
     *
     * @param str
     * @param l
     * @param m
     */
    private static void swap(int[] str, int l, int m) {
        int temp = str[l];
        str[l] = str[m];
        str[m] = temp;
    }

    /**
     * Method to reverse the elements in the array between the given indexes
     *
     * @param str
     * @param l
     * @param m
     */
    private static void reverse(int[] str, int l, int m) {
        while (l < m) {
            swap(str, l, m);
            l++;
            m--;
        }
    }

    /**
     * Method to find the index of the element in the given array which is just greater than the given threshold but
     * less than the other elements for the given interval between start & end
     *
     * @param nums
     * @param threshold
     * @param start
     * @param end
     */
    private static int findCeiling(int[] nums, int threshold, int start, int end) {
        int ceiling = start;

        for (int i = start; i < end; i++) {
            if (nums[i] > threshold && nums[i] < nums[ceiling]) {
                ceiling = i;
            }
        }
        return ceiling;
    }

    /**
     * Method to provide with the next permutation of the array in a lexicographic order
     * e.g. 012 -> 021 -> 102 -> 120 -> 201 -> 210
     *
     * @param nums
     */
    private static boolean nextPermutation(int[] nums) {
        int size = nums.length;

        int i;
        for (i = size - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) { break; }
        }
        if (i < 0) { return true; }

        int next = findCeiling(nums, nums[i], i + 1, size);
        swap(nums, i, next);
        reverse(nums, i + 1, size - 1);
        return false;
    }

    /**
     * Method to get string representation of the pairs
     * Depending on the return value, we go clockwise from that direction
     *
     * @param exts
     * @param sides
     */
    private static String getString(int[] exts, int[] sides) {
        String min = getMin(exts);
        StringBuilder sb = new StringBuilder();

        int i = 0;
        switch (min) {
            case "ext1":
                i = 0;
                break;
            case "ext2":
                i = 1;
                break;
            case "ext3":
                i = 2;
                break;
            case "ext4":
                i = 3;
                break;
            case "ext5":
                i = 4;
                break;
        }
        for (int j = 0; j < exts.length; j++) {
            sb.append(exts[i % exts.length]).append(sides[i % sides.length]).append(sides[(i + 1) % sides.length]);
            i++;
        }
        return sb.toString();
    }

    /**
     * Method to return the minimum element from the given array
     *
     * @param exts
     */
    private static String getMin(int[] exts) {
        int min = Math.min(exts[0], exts[1]);
        min = Math.min(min, exts[2]);
        min = Math.min(min, exts[3]);
        min = Math.min(min, exts[4]);

        if (min == exts[0]) { return "ext1"; }
        if (min == exts[1]) { return "ext2"; }
        if (min == exts[2]) { return "ext3"; }
        if (min == exts[3]) { return "ext4"; }
        return "ext5";
    }

    /**
     * Main method to execute the code
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<String> pairs = getPairs();

        BigInteger max = BigInteger.ZERO;
        for (String pair : pairs) {
            if (pair.length() != 16) { continue; }
            BigInteger num = new BigInteger(pair);
            if (num.max(max).equals(num)) {
                max = num;
            }
        }
        System.out.println(max.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
