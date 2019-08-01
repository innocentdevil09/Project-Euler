public class Problem1 {

    /**
     * Method to return sum of all multiples in range from 1 to num, for the given multiple
     *
     * @param num
     * @param multiple
     */
    private static int sumOfMultiples(int num, int multiple) {
        if (num <= 0 || multiple <= 0) {
            throw new IllegalArgumentException("Illegal arguments: " + num + ", " + multiple);
        }
        int sum = 0;
        int factor = multiple;
        while (multiple < num) {
            sum += multiple;
            multiple += factor;
        }
        return sum;
    }

    public static void main(String[] args) {
        int result = sumOfMultiples(1000, 3) + sumOfMultiples(1000, 5) - sumOfMultiples(1000, 15);
        System.out.println(result);
    }
}