import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem44 {

    private static List<Integer> getAllPentagonNumbers() {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; ; i++) {
            int val = (((3 * i) - 1) * i) / 2;
            if (val < 0) { break; }
            result.add(val);
        }
        return result;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Integer> list = getAllPentagonNumbers();
        Set<Integer> set = new HashSet<>(list);

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i; j++) {
                int sum = list.get(i) + list.get(j);
                int diff = Math.abs(list.get(i) - list.get(j));
                if (set.contains(sum) && set.contains(diff) && diff < minDiff) {
                    minDiff = diff;
                }
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Minimum difference with conditions - " + minDiff);
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}