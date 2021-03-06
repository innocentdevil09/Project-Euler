import java.util.Arrays;

public class Problem52 {

    /**
     * Method to generate an array containing the count of each digit's occurence as there may be duplicates
     *
     * @param digits
     */
    private static int[] getCountedArray(char[] digits) {
        int[] countedArray = new int[10];
        for (char digit : digits) {
            switch (digit) {
                case '0':
                    countedArray[0] += 1;
                case '1':
                    countedArray[1] += 1;
                case '2':
                    countedArray[2] += 1;
                case '3':
                    countedArray[3] += 1;
                case '4':
                    countedArray[4] += 1;
                case '5':
                    countedArray[5] += 1;
                case '6':
                    countedArray[6] += 1;
                case '7':
                    countedArray[7] += 1;
                case '8':
                    countedArray[8] += 1;
                case '9':
                    countedArray[9] += 1;
            }
        }
        return countedArray;
    }

    /**
     * This method is used to check if the number and its multiplier has the same digits
     *
     * @param i
     * @param multiply
     */
    private static boolean matchesMultiplier(int i, int multiply) {
        char[] x = String.valueOf(i).toCharArray();
        char[] multiplier = String.valueOf(i * multiply).toCharArray();
        if (multiplier.length != x.length) { return false; }
        int[] xArray = getCountedArray(x);
        int[] multiplierArray = getCountedArray(multiplier);
        return Arrays.equals(xArray, multiplierArray);
    }

    /**
     * Main method to get the answer. Time taken - 80 ms
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int i = 10;
        for (; i < Integer.MAX_VALUE; i++) {
            if (!matchesMultiplier(i, 6)) { continue; }
            if (!matchesMultiplier(i, 5)) { continue; }
            if (!matchesMultiplier(i, 4)) { continue; }
            if (!matchesMultiplier(i, 3)) { continue; }
            if (!matchesMultiplier(i, 2)) { continue; }
            break;
        }
        System.out.println("Answer = " + i);
        long endTime = System.currentTimeMillis();
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}