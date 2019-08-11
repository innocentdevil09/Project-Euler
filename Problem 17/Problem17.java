import java.util.HashMap;
import java.util.Map;

public class Problem17 {

    private static final Map<Integer, String> numWordMap = new HashMap<>();

    static {
        numWordMap.put(0, "");
        numWordMap.put(1, "one");
        numWordMap.put(2, "two");
        numWordMap.put(3, "three");
        numWordMap.put(4, "four");
        numWordMap.put(5, "five");
        numWordMap.put(6, "six");
        numWordMap.put(7, "seven");
        numWordMap.put(8, "eight");
        numWordMap.put(9, "nine");
        numWordMap.put(10, "ten");
        numWordMap.put(11, "eleven");
        numWordMap.put(12, "twelve");
        numWordMap.put(13, "thirteen");
        numWordMap.put(14, "fourteen");
        numWordMap.put(15, "fifteen");
        numWordMap.put(16, "sixteen");
        numWordMap.put(17, "seventeen");
        numWordMap.put(18, "eighteen");
        numWordMap.put(19, "nineteen");
        numWordMap.put(20, "twenty");
        numWordMap.put(30, "thirty");
        numWordMap.put(40, "forty");
        numWordMap.put(50, "fifty");
        numWordMap.put(60, "sixty");
        numWordMap.put(70, "seventy");
        numWordMap.put(80, "eighty");
        numWordMap.put(90, "ninety");
        numWordMap.put(100, "onehundred");
        numWordMap.put(200, "twohundred");
        numWordMap.put(300, "threehundred");
        numWordMap.put(400, "fourhundred");
        numWordMap.put(500, "fivehundred");
        numWordMap.put(600, "sixhundred");
        numWordMap.put(700, "sevenhundred");
        numWordMap.put(800, "eighthundred");
        numWordMap.put(900, "ninehundred");
    }

    /**
     * Method to return word representation of the given number
     *
     * @param num
     */
    private static String convertToWord(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Require positive integers!");
        }
        if (num < 21 || num % 100 == 0) {
            return numWordMap.get(num);
        }
        StringBuilder sb = new StringBuilder();
        if (num > 100) {
            sb.append(numWordMap.get((num / 100) * 100)).append("and");
            num = num % 100;
        }
        if (num > 20) {
            sb.append(numWordMap.get((num / 10) * 10));
            num = num % 10;
        }
        sb.append(numWordMap.get(num));
        return sb.toString();
    }

    public static void main(String[] args) {
        int wordLength = 0;
        for (int i = 1; i < 1000; i++) {
            String word = convertToWord(i);
            wordLength += word.length();
        }
        wordLength += "onethousand".length();
        System.out.println(wordLength);
    }

}
