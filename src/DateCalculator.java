public class DateCalculator {

    static final int LEAP_YEAR = 366;
    static final int REGULAR_YEAR = 365;
    static final int FIRST = 1;
    static final int LAST_MONTH = 12;
    static final int LAST = 31;
    static final int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isLeapYear (int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public static int daysTillNextMonth (int day, int month, int year){
        if (isLeapYear(year) && month == 2 && day <= 29) return (29 - day + 1);
        return DAYS[month-1] - day + 1;
    }

    public static int daysTillNextYear (int day, int month, int year){
        int result = 0;
        if(isLeapYear(year) && month <= 2 && day < 29) result++;
        result += daysTillNextMonth(day, month, year);
        for (int i = month; i < LAST_MONTH; i++){
            result += DAYS[i];
        }
        return result;
    }

    public static int daysTillPrevYear (int day, int month, int year){
        if (isLeapYear(year)) return (LEAP_YEAR - daysTillNextYear(day, month, year) +1);
        else return (REGULAR_YEAR - daysTillNextYear(day, month, year) +1);
    }

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

