public class DateCalculator {

    final int leapYear 366
    final int regularYear 365
    final int[] DaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    public static boolean isLeapYear (int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
    }

    // Function to return the day number
    // of the year for the given date
    static int dayOfYear(int day, int month)
    {
        if (month <= 1)
            return day;
        month--;
        dayOfYear(day + DaysInMonth[month], month);
    }

    public static Date addToDate(Date date, int num) {
        if (num == 0) return date;
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        int currYearDay = dayOfYear(int day, int month);
        if ((isLeapYear(year)) && month>2)
            currYearDay++;
        if (num<0) {

        }
        else {

        }


    }

}
