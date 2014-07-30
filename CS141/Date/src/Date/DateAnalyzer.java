/*
 * DateAnalyzer.java
 */
package Date;
import java.util.*;
/**
 * Date Analyzer <br>
 * <br>
 * Program that compares inputed dates the current dates <br>
 * <br>
 * Test Enviroment: JDK 1.7.*** on Windows 7, i7 CPU <br>
 * Test Enviroment: JDK 1.7.*** on Windows 8, AMD CPU <br>
 * <br>
 * November 14th 2012 <br>
 * 	Completed v 1.0 <br>
 * @author Brandan Haertel <br>
 * @version 1.0 <br>
 */
public class DateAnalyzer {
    /**
     * Creates a new date object using only current dates.
     */
    DateAnalyzer() {
        SetInputDate(   GetCurrentDay(),
                        GetCurrentMonth(),
                        GetCurrentYear());
    }
    /**
     * Creates a new date object using an inputed day with
     * current month and year.
     * @param inputDay 
     */
    DateAnalyzer(short inputDay) {
        SetInputDate(   inputDay,
                        GetCurrentMonth(),
                        GetCurrentYear());
    }
    /**
     * Creates a new date object using an inputed day and month
     * with current year.
     * @param inputDay
     * @param inputMonth 
     */
    DateAnalyzer(short inputDay, short inputMonth) {
        short monthConvert = (short) (inputMonth - 1); 
        SetInputDate(inputDay,
                        monthConvert,
                        GetCurrentYear());
    }
    /**
     * Creates a new date object using an inputed day, month, and year.
     * @param inputDay
     * @param inputMonth
     * @param inputYear 
     */
    DateAnalyzer(short inputDay, short inputMonth, short inputYear) {
        short monthConvert = (short) (inputMonth - 1);
        SetInputDate(inputDay,
                        monthConvert,
                        inputYear);
    }
    /**
     * Verifies date against max and min constraints and sets private
     * global variables.
     * @param inDay sets the inputed day.
     * @param inMonth sets the inputed month.
     * @param inYear  sets the inputed year.
     */
    public void SetInputDate(short inDay, short inMonth, short inYear){
        if(inMonth > 11){inMonth = 11;}
        else if(inMonth < 0){inMonth = 0;}
        
        if(isLeap(inYear)) {maxDaysOfMonth[1] = 29;}
        
        if(inDay > maxDaysOfMonth[inMonth]) 
        {dayOfMonth = maxDaysOfMonth[inMonth];}
        else if(inDay < minDaysOfMonth) {dayOfMonth = minDaysOfMonth;}
        else {dayOfMonth = inDay;}
        
        if(inMonth > 11) {month = 11;}
        else if(inMonth < 0) {month = 1;}
        else {month = inMonth;}
        
        if(inYear < 1700) {year = 1700;}
        else {year = inYear;}
        
        //reset days of feburary
        maxDaysOfMonth[1] = 28;
    }
    /**
     * gets the day before the inputed day.
     * @return the day before string.
     */
    public String GetDateBefore() {
        short dayOfMonthBefore = (short) (dayOfMonth - 1);
        short monthBefore = month;
        short yearBefore = year;
        
        if(isLeap(yearBefore)) {maxDaysOfMonth[1] = 29;}
        
        if(dayOfMonthBefore < minDaysOfMonth) 
        {
            dayOfMonthBefore = maxDaysOfMonth[monthBefore - 1];
            monthBefore -= 1;
            if(monthBefore < 0)
            {
                monthBefore = 11;
                yearBefore -= 1;
            }
        }
        
        //reset days of feburary
        maxDaysOfMonth[1] = 28;
        String str = "The date Before is: " 
		+ calculateDayOfWeek(dayOfMonthBefore,monthBefore,yearBefore) 
		+ ", " + dayOfMonthBefore + " " + GetMonthString(monthBefore) 
                + ", " + yearBefore;
        return str;
    }
    /**
     * gets the day after the inputed day.
     * @return the day after string.
     */
    public String GetDateAfter() {
        short dayOfMonthAfter = (short) (dayOfMonth + 1);
        short monthAfter = month;
        short yearAfter = year;
        
        if(isLeap(yearAfter)) {maxDaysOfMonth[1] = 29;}
        
        if(dayOfMonthAfter > maxDaysOfMonth[monthAfter]) 
        {
            dayOfMonthAfter = minDaysOfMonth;
            monthAfter += 1;
            if(monthAfter > 11)
            {
                monthAfter = 0;
                yearAfter += 1;
            }
        }
        
        //reset days of feburary
        maxDaysOfMonth[1] = 28; 
        String str = "The date After is: " 
		+ calculateDayOfWeek(dayOfMonthAfter,monthAfter,yearAfter) 
		+ ", " + dayOfMonthAfter + " " + GetMonthString(monthAfter) 
                + ", " + yearAfter;
        return str;
    }
    /**
     * gets the current system date.
     * @return current system date string
     */
    public String GetCurrentDate() {
        String str = "The current date is: " 
		+ calculateDayOfWeek
                (GetCurrentDay(),GetCurrentMonth(),GetCurrentYear())
		+ ", " + GetCurrentDay() + " " 
                + GetMonthString(GetCurrentMonth()) + ", " + GetCurrentYear();
        return str;
    }
    /**
     * gets the inputed date.
     * @return inputed date string.
     */
    public String GetInputedDate() {
        String str = "The input date is: " 
		+ calculateDayOfWeek(dayOfMonth,month,year) 
		+ ", " + dayOfMonth + " " + GetMonthString(month) + ", " + year;
        return str;
    }
    /**
     * 
     * @param in month value to set month string.
     * @return month string
     */
    public String GetMonthString(short in) {
        String str = "";
        for(int i = 0; i < 12 ; i++) {
            if(i == in) {str = monthString[i];}
        }
        return str;
    }
    /**
     * 
     * @param inYear year value to determine if leap year.
     * @return true if inyear is leap year.
     */
    public boolean isLeap( int inYear ) {
	if ( inYear % 4 == 0 && inYear % 100 != 0 ) {return true;}
	else if ( inYear % 4 == 0 && inYear % 100 == 0 ) {
            if ( inYear % 400 == 0 ) {return true;}
	}
	return false;
    }
    /**
     * gets the string of the day of week.
     * @param inday day of the month.
     * @param inmonth month of the year.
     * @param inyear year
     * @return weekday string
     */
    public String calculateDayOfWeek( int inday, int inmonth, int inyear ) {
	int startDay = 0;
        int daysOfYear = 365;
	int totalDays = 0;
	String finalDay = "";

        if(isLeap(inyear)) {maxDaysOfMonth[1] = 29;}
                
	int i = startYear;
	while ( i < inyear ) {
			
		if (isLeap(i)) {totalDays += 1;}

		totalDays += daysOfYear;
		i += 1;
	}

	for ( int j = 0; j < inmonth; j++ ) {
        	totalDays += maxDaysOfMonth[j];
        }

        totalDays += inday;

        startDay = totalDays % 7;
        finalDay = strDaysOfWeek[startDay];
	
        maxDaysOfMonth[1] = 28;

	return finalDay;
    }
    /**
     * finds if the inputed date is before or after the current date.
     * @return string representing tense.
     */
    public String GetPastOrFuture() {
        int daysOfYear = 365;
	int totalDaysCurrent = 0;
        int totalDaysInput = 0;
	String result = "";
        
        //calculate how many days from jan 1st 1760 to now
        if(isLeap(GetCurrentYear())) {maxDaysOfMonth[1] = 29;}   
	int i = startYear;
        
	while ( i < GetCurrentYear() ) {
			
		if (isLeap(i)) {totalDaysCurrent += 1;}
		totalDaysCurrent += daysOfYear;
		i += 1;
	}
	for ( int j = 0; j < GetCurrentMonth(); j++ ) {
        	totalDaysCurrent += maxDaysOfMonth[j];
        }
        totalDaysCurrent += GetCurrentDay();
        maxDaysOfMonth[1] = 28;
        
        //calculate how many days from jan 1st 1760 to the inputer date
        if(isLeap(year)) {maxDaysOfMonth[1] = 29;}
	i = startYear;
	
        while ( i < year ) {	
		if (isLeap(i)) {totalDaysInput += 1;}
		totalDaysInput += daysOfYear;
		i += 1;
	}
	for ( int j = 0; j < month; j++ ) {
        	totalDaysInput += maxDaysOfMonth[j];
        }
        totalDaysInput += dayOfMonth;
        maxDaysOfMonth[1] = 28;
        if(totalDaysCurrent > totalDaysInput)
        {
            result = GetInputedDate().substring(19,GetInputedDate().length())
                    + " Is in the Past!";
        }
        else if(totalDaysCurrent < totalDaysInput)
        {
            result = GetInputedDate().substring(19,GetInputedDate().length())
                    + " Is in the Future!";
        }
        else
        {
            result = GetInputedDate().substring(19,GetInputedDate().length())
                    + " Is the Present!";
        }
	return result;
    }
    /**
     * gets the system current day of month date.
     * @return current day of month short.
     */  
    public short GetCurrentDay() {
        GregorianCalendar cal = new GregorianCalendar();
        short gDay = (short)cal.get(GregorianCalendar.DAY_OF_MONTH);
        return gDay;
    }
    /**
     * gets the system current month.
     * @return current month short
     */    
    public short GetCurrentMonth() {
        GregorianCalendar cal = new GregorianCalendar();
        short gMonth = (short)cal.get(GregorianCalendar.MONTH);
        return gMonth;
    }
    /**
     * gets the system current year.
     * @return current year short.
     */
    public short GetCurrentYear() {
        GregorianCalendar cal = new GregorianCalendar();
        short gYear = (short)cal.get(GregorianCalendar.YEAR);
        return gYear;
    }
    
    private short dayOfMonth = 0;
    private short month = 0;
    private short year = 0;
    private final short startYear = 1760;
    private final short minDaysOfMonth = 1;
    private short[] maxDaysOfMonth = 
        {31,28,31,30,31,30,31,31,30,31,30,31};
    private final String[] monthString = {
        "January",  "Feburary", "March",
        "April",    "May",      "June",
        "July",     "August",   "September",
        "October",  "November", "December" };
    private final String[] strDaysOfWeek = {
	"Monday", "Tuesday",  "Wednesday", "Thursday",
	"Friday", "Saturday", "Sunday"}; 
}