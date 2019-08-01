public class Problem4 {

    private static final int NO_OF_DIGITS = 3;

    /**
     * Method to check if a given number is a palindrome
     *
     * @param num
     */
    private static boolean isPalindrome(int num) {
        String number = String.valueOf(num);
        int i = -1, j = -1;

        if (number.length() % 2 == 0) {
            i = (number.length() / 2) - 1;
            j = number.length() / 2;
        } else {
            i = (number.length() / 2) - 1;
            j = (number.length() / 2) + 1;
        }

        while (i > -1 && j < number.length() && number.charAt(i) == number.charAt(j)) {
            i--;
            j++;
        }
        return i == -1 && j == number.length();
    }

    public static void main(String[] args) {
        int highestNumber = (int) Math.pow(10, NO_OF_DIGITS) - 1;
        int lowestNumber = (int) Math.pow(10, NO_OF_DIGITS - 1);

        int result = -1;
        for (int i = highestNumber; i > lowestNumber; i--) {
            for (int j = highestNumber; j > highestNumber - lowestNumber; j--) {
                int num = i * j;
                if (isPalindrome(num)) {
                    System.out.println("Factors: " + i + ", " + j);
                    result = num;
                    break;
                }
            }
            if (result != -1) {
                break;
            }
        }
        System.out.println(result);
    }
}
