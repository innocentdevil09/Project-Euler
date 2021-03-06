import java.util.Calendar;

public class Problem19 {

    private static final String[] DAYS =
            {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        int count = 0;
        for (int year = 1901; year <= 2000; year++) {
            for (int month = 01; month <= 12; month++) {
                c.set(year, month, 01);
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                if (DAYS[dayOfWeek].equals("Sunday")) { count++; }
            }
        }
        System.out.println(count);
    }
}
