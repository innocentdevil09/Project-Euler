public class Problem40 {

    public static void main(String[] args) {
        String decimal = getIrrationalDecimal();
        int d1 = decimal.charAt(0) - '0';
        int d10 = decimal.charAt(9) - '0';
        int d100 = decimal.charAt(99) - '0';
        int d1000 = decimal.charAt(999) - '0';
        int d10000 = decimal.charAt(9999) - '0';
        int d100000 = decimal.charAt(99999) - '0';
        int d1000000 = decimal.charAt(999999) - '0';

        int product = d1 * d10 * d100 * d1000 * d10000 * d100000 * d1000000;

        System.out.println("Result - " + product);
    }

    /**
     * Method to get a decimal number by appending digits to the string for a maximum length till 1000000 length
     */
    private static String getIrrationalDecimal() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; sb.length() < 1000000; i++) {
            sb.append(i);
        }
        return sb.toString();
    }
}
