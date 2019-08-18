import java.util.ArrayList;
import java.util.List;

public class Problem50 {

    private static boolean isPrimeNumber(int n) {
        if (n == 2) { return true; }
        if (n < 2 || n % 2 == 0) { return false; }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<Integer> primeNbrs = new ArrayList<>();
        int count = 0, result = 0, mark = 1000000;
        for (int i = 1; ; i++) {
            if (isPrimeNumber(i)) {
                result += i;
                primeNbrs.add(i);
                count++;
                if (result > mark) {
                    result -= i;
                    break;
                }
            }
        }
        int j = 0;
        while (!isPrimeNumber(result)) {
            result -= primeNbrs.get(j);
            count--;
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Largest consecutive prime sum - " + result + " with count - " + count);
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}