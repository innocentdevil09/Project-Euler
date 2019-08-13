import java.math.BigDecimal;
import java.math.RoundingMode;

public class Problem26 {

    /**
     * Main method to run loop over 1000 digits to get recurrence length in their decimals, if any
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int result = 0, max = 0;
        for (int i = 2; i < 1000; i++) {
            BigDecimal num = BigDecimal.ONE.divide(BigDecimal.valueOf(i), 2100, RoundingMode.HALF_UP);
            int val = recurrenceLength(num);
            if (val > max) {
                max = val;
                result = i;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Recurrence length : " + max);
        System.out.println("Digit : " + result);
        System.out.println("Time taken in milli-secs : " + (endTime - startTime));
    }

    /**
     * Find recurrence length in the 20,000 digit decimal thanks to BigDecimal. Algo :
     * 1. Run a loop over the number as re-occurring digit may lie at any place
     * 2. Find next index && next-to-next index of that digit
     * 3. Keep increasing the re-occurring number length until it is less than the initial difference
     *
     * @param num
     */
    private static int recurrenceLength(BigDecimal num) {
        String value = num.toPlainString();
        int result = 0;
        for (int i = 2; i < value.length(); i++) {
            int index = value.indexOf(value.charAt(i), i + 1);
            int nextIndex = value.indexOf(value.charAt(i), index + 1);
            if (index == -1 || nextIndex == -1) { continue; }

            int diff = index - i;

            while (nextIndex - index < diff) {
                index = nextIndex;
                nextIndex = value.indexOf(value.charAt(i), index + 1);
                if (nextIndex == -1) { break; }
            }

            if (index + index - i < value.length() && index - i > result && value.substring(i, index)
                    .equals(value.substring(index, index + index - i))) {
                result = index - i;
            }
        }
        return result;
    }
}