import java.util.HashSet;
import java.util.Set;

public class Problem38 {

    /**
     * The objective is to find the largest pan-digital number that is formed by concatenating product values of a
     * number
     * Since the objective is to find the largest number, it is obvious that the number should start with digit 9.
     * Hence it will start with 9, then 90 to 99, then 900 to 999 and so on.
     * The value of MAX is limited to 4 to indicate the number of digits that we will look at max till 9999. This is
     * because any five digit number multiplied by 1 & 2 and concatenated together gives 10 digit number which cannot be
     * a pan-digital number
     */
    private static final int MAX = 4;

    /**
     * Method to determine if a given number is a pan-digital number
     *
     * @param num
     */
    private static boolean isPanDigitalNumber(String num) {
        if (num.length() != 9) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < num.length(); i++) {
            int val = num.charAt(i) - '0';
            if (val == 0) { continue; }
            set.add(val);
        }
        return set.size() == 9;
    }

    public static void main(String[] args) {
        long largest = -1L;
        for (int digit = 1; digit <= MAX; digit++) {

            for (int i = (int)Math.pow(10, digit) - (int) Math.pow(10, digit - 1); i < (int) Math.pow(10, digit); i++) {
                StringBuilder sb = new StringBuilder();

                for (int j = 1; j <= i && sb.length() < 9; j++) {
                    sb.append(i * j);
                }
                if (Long.parseLong(sb.toString()) > largest && isPanDigitalNumber(sb.toString())) {
                    largest = Integer.parseInt(sb.toString());
                }
            }
        }
        System.out.println(largest);
    }
}
