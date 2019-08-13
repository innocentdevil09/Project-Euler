public class Problem30 {

    private static long getExponentSum(long num, long exponent) {
        long sum = 0L;

        while (num > 0) {
            sum += (long) Math.pow(num % 10, exponent);
            num = num / 10L;
        }
        return sum;
    }

    public static void main(String[] args) {
        long result = 0L;

        for (int i = 2; i < 1000000; i++) {
            long sum = getExponentSum((long) i, 5L);
            if (sum == i) {
                result += i;
            }
        }
        System.out.println(result);
    }
}
