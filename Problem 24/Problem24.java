public class Problem24 {

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

        for (int i = start + 1; i < end; i++) {
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
    private static void nextPermutation(int[] nums) {
        int size = nums.length;

        boolean isFinished = false;
        while (!isFinished) {
            int i;
            for (i = size - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    break;
                }
            }
            if (i < 0) {
                isFinished = true;
            } else {
                int next = findCeiling(nums, nums[i], i + 1, size);
                swap(nums, i, next);
                reverse(nums, i + 1, size - 1);
                break;
            }
        }
    }

    /**
     * Method to print out the one millionth number in the lexicographic series
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 1; i < 1000000; i++) {
            nextPermutation(array);
        }

        for (int i : array) {
            System.out.print(i);
        }
    }
}
