public class DateCalculator {

    static final int LEAP_YEAR = 366;
    static final int REGULAR_YEAR = 365;
    static final int FIRST = 1;
    static final int LAST_MONTH = 12;
    static final int[] DAYS = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    public static boolean isLeapYear (int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
    }
    public static boolean isLastDay (int day, int month) {
        return (day == 31 && month == 12)
    }
    public static boolean isFirstDay (int day, int month)
        return (day == 1 && month == 1)

    public static Date addToDate(Date date, int num) {

        if (num == 0) return date;
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        boolean isLeap = isLeapYear(year);
        if (num>0) {
            if (day == DAYS[month]) && ((!isLeap) || (isLeap && day == DAYS[month]+1) {
                month++;
                if (month == LAST_MONTH) {
                    year++;
                    day = FIRST;
                    month = FIRST;
                }
            }
            day++;
            Date date1 = Date(day, month, year);
            Date newDate =  addToDate(date1, num--);
            return newDate;
        }

        else {
            if (day == FIRST) {
                if (month == FIRST) {
                    year--;
                    month = LAST_MONTH;
                    day = DAYS[LAST_MONTH];
                }
            }
            day--;
            Date date2 = Date(day, month, year);
            Date newDate =  addToDate(date2, num++);
            return newDate;
        }

}

// Function to return the day number
        // of the year for the given date
/*    static int dayOfYear(int day, int month)
    {
        if (month <= 1)
            return day;
        month--;
        dayOfYear(day + DAYS[month], month);
    }*/

