public class DateCalculator {
    /**
     * This class represents a date calculator.
     * We defined constant numbers:
     * number of days in a regular year and in a leap year.
     * constant array of the number of days in each month.
     */

    static final int LEAP_YEAR = 366;
    static final int REGULAR_YEAR = 365;
    static final int FIRST = 1;
    static final int LAST_MONTH = 12;
    static final int LAST = 31;
    static final int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * Returns if the given year is a leap year.
     * @param year - the year we want to check.
     * @return true if leap year, false if regular year.
     */
    public static boolean isLeapYear (int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    /**
     * calculates and returns number of days until next month
     * @param day - int type, current day
     * @param month - int type, current month
     * @param year - int type, current year
     * @return - int type, number of days until next month.
     */
    public static int daysTillNextMonth (int day, int month, int year){
        if (isLeapYear(year) && month == 2 && day <= 29) return (29 - day + 1);
        return DAYS[month-1] - day + 1;
    }
    /**
     * calculates and returns number of days until next year
     * @param day - int type, current day
     * @param month - int type, current month
     * @param year - int type, current year
     * @return - int type, number of days until next year.
     */
    public static int daysTillNextYear (int day, int month, int year){
        int result = 0;
        if(isLeapYear(year) && month <= 2 && day < 29) result++;
        result += daysTillNextMonth(day, month, year);
        for (int i = month; i < LAST_MONTH; i++){
            result += DAYS[i];
        }
        return result;
    }

    /**
     * calculates and returns the number of the day in this year. uses daysTillNextYear function.
     * @param day - int type, current day
     * @param month - int type, current month
     * @param year - int type, current year
     * @return - int type, the number of this day in the current year.
     */
    public static int daysTillPrevYear (int day, int month, int year){
        if (isLeapYear(year)) return (LEAP_YEAR - daysTillNextYear(day, month, year) +1);
        else return (REGULAR_YEAR - daysTillNextYear(day, month, year) +1);
    }

    /**
     * by a given date and number of days before or after it, calculates and returns the required date.
     * @param date - the date we want to add/subtract days from.
     * @param num - number of days we want to add (if positive number)
     *           or subtract (if negative number) from a given date.
     * @return - the calculated date.
     */
    public static Date addToDate(Date date, int num) {

        if (num == 0) return date;
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        boolean isLeap = isLeapYear(year);

        // case of adding days
        if (num>0) {
            // for big values of 'num' we want to jump across years/months at once
            int endOfYear = daysTillNextYear(day, month, year);
            int endOfMonth = daysTillNextMonth(day, month, year);
            if (num >= endOfYear)
                return addToDate(new Date(FIRST, FIRST, year+1), num - endOfYear);
            else if (num >= endOfMonth) {
                if (month == LAST_MONTH) return addToDate(new Date(FIRST, FIRST, year+1), num - endOfMonth);
                else return addToDate(new Date(FIRST, month+1, year), num - endOfMonth);
            }

            //in case we are on the last days of February in a leap year
            else if (isLeap && month==2) {
                if (day == 28) day++;
                else if (day==29) {
                    month++;
                    day = FIRST;
                }
            }
            // no special case
            else day++;
            Date date1 = new Date(day, month, year);
            return addToDate(date1, num-1);
        }

        // case of subtracting days
        else {
            // for big values of 'num' we want to jump across years/months at once
            int startOfYear = daysTillPrevYear(day, month, year);
            int StartOfMonth = day;
            if (num*(-1) >= startOfYear)
                return addToDate(new Date(LAST, LAST_MONTH, year-1), num + startOfYear);
            else if (num*(-1) >= day)
               return addToDate(new Date(DAYS[month-2], month-1, year), num + StartOfMonth);

            // in case we're making a jump from March to Fab
            else if (isLeap && (month == 3) && (day == FIRST)) {
                day = 29;
                month--;
            }
            // no special case
            else day--;
            Date date2 = new Date(day, month, year);
            return addToDate(date2, num+1);
        }
    }
}
