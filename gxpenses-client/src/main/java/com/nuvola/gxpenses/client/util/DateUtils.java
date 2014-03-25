package com.nuvola.gxpenses.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.nuvola.gxpenses.common.shared.type.FrequencyType;
import com.nuvola.gxpenses.common.shared.type.PeriodType;

public class DateUtils {

    private static final String D_M_YYYY = "d-M-yyyy";
    private static final String DATE_SEPARATOR = "-";

    public static Integer JANUARY = 1;
    public static Integer FEBRUARY = 2;
    public static Integer MARCH = 3;
    public static Integer APRIL = 4;
    public static Integer MAY = 5;
    public static Integer JUNE = 6;
    public static Integer JULY = 7;
    public static Integer AUGUST = 8;
    public static Integer SEPTEMBER = 9;
    public static Integer OCTOBER = 10;
    public static Integer NOVEMBER = 11;
    public static Integer DECEMBER = 12;

    /**
     * Return a Date Object from day, mnth, year
     *
     * @param dd   => The Day
     * @param mm   => The Month
     * @param yyyy => The year
     * @return Date having dd,m,yyyy configured
     */
    public static Date getDate(Integer dd, Integer mm, Integer yyyy) {
        if (dd == null || mm == null || yyyy == null)
            return null;

        Date retVal = null;
        try {
            retVal = DateTimeFormat.getFormat(D_M_YYYY).parse(
                    dd + DATE_SEPARATOR + mm + DATE_SEPARATOR + yyyy);
        } catch (Exception e) {
            retVal = null;
        }

        return retVal;
    }

    /**
     * Return the Day of the Date
     *
     * @param date
     * @return Day as String
     */
    public static String getDayAsString(Date date) {
        return (date == null) ? null : DateTimeFormat.getFormat(D_M_YYYY)
                .format(date).split(DATE_SEPARATOR)[0];
    }

    /**
     * Return the Month of the Date
     *
     * @param date
     * @return Month as String
     */
    public static String getMonthAsString(Date date) {
        return (date == null) ? null : DateTimeFormat.getFormat(D_M_YYYY)
                .format(date).split(DATE_SEPARATOR)[1];
    }

    /**
     * Return the Year of the Date
     *
     * @param date
     * @return Year as String
     */
    public static String getYearAsString(Date date) {
        return (date == null) ? null : DateTimeFormat.getFormat(D_M_YYYY)
                .format(date).split(DATE_SEPARATOR)[2];
    }

    public static String getDateToDisplay(Date date, FrequencyType frequency) {
        Integer month = Integer.parseInt(getMonthAsString(date));
        Integer year = Integer.parseInt(getYearAsString(date));
        String dateToDisplay = null;

        switch (frequency) {
            case MONTH:
                dateToDisplay = DateTimeFormat.getFormat("MMMM yyyy").format(date);
                break;
            case QUARTER:
                if (month >= JANUARY && month <= MARCH)
                    dateToDisplay = "First Quarter Of " + year;
                else if (month >= APRIL && month <= JUNE)
                    dateToDisplay = "Second Quarter Of " + year;
                else if (month >= JULY && month <= SEPTEMBER)
                    dateToDisplay = "Third Quarter Of " + year;
                else if (month >= OCTOBER && month <= DECEMBER)
                    dateToDisplay = "Fourth Quarter Of " + year;
                break;
            case SEMESTER:
                if (month >= JANUARY && month <= JUNE)
                    dateToDisplay = "First Semester Of " + year;
                else if (month >= JULY && month <= DECEMBER)
                    dateToDisplay = "Second Semester Of " + year;
                break;
            case YEAR:
                dateToDisplay = "Year " + year;
        }

        return dateToDisplay;
    }

    public static String getDateToDisplay(PeriodType period) {
        Date now = new Date();
        String dateToDisplay = "";

        if (period == PeriodType.THIS_MONTH) {
            dateToDisplay = DateTimeFormat.getFormat("MMMM yyyy").format(now);
        } else if (period == PeriodType.LAST_MONTH) {
            now = getPreviousDate(now, FrequencyType.MONTH);
            dateToDisplay = DateTimeFormat.getFormat("MMMM yyyy").format(now);
        } else if (period == PeriodType.THIS_QUARTER) {
            dateToDisplay = getDateToDisplay(now, FrequencyType.QUARTER);
        } else if (period == PeriodType.LAST_QUARTER) {
            now = getPreviousDate(now, FrequencyType.QUARTER);
            dateToDisplay = getDateToDisplay(now, FrequencyType.QUARTER);
        } else if (period == PeriodType.THIS_SEMESTER) {
            dateToDisplay = getDateToDisplay(now, FrequencyType.SEMESTER);
        } else if (period == PeriodType.LAST_SEMESTER) {
            now = getPreviousDate(now, FrequencyType.SEMESTER);
            dateToDisplay = getDateToDisplay(now, FrequencyType.SEMESTER);
        } else if (period == PeriodType.THIS_YEAR) {
            dateToDisplay = getDateToDisplay(now, FrequencyType.YEAR);
        } else if (period == PeriodType.LAST_YEAR) {
            now = getPreviousDate(now, FrequencyType.YEAR);
            dateToDisplay = getDateToDisplay(now, FrequencyType.YEAR);
        }

        return dateToDisplay;
    }

    public static Date getNextDate(Date date, FrequencyType frequency) {
        Integer month = Integer.parseInt(getMonthAsString(date));
        Integer year = Integer.parseInt(getYearAsString(date));
        Date nextDate = null;

        switch (frequency) {
            case MONTH:
                if (month == DECEMBER)
                    nextDate = getDate(1, JANUARY, year + 1);
                else
                    nextDate = getDate(1, month + 1, year);
                break;
            case QUARTER:
                if (month >= JANUARY && month <= MARCH)
                    nextDate = getDate(1, APRIL, year);
                else if (month >= APRIL && month <= JUNE)
                    nextDate = getDate(1, JULY, year);
                else if (month >= JULY && month <= SEPTEMBER)
                    nextDate = getDate(1, OCTOBER, year);
                else if (month >= OCTOBER && month <= DECEMBER)
                    nextDate = getDate(1, JANUARY, year + 1);
                break;
            case SEMESTER:
                if (month >= JANUARY && month <= JUNE)
                    nextDate = getDate(1, JULY, year);
                else if (month >= JULY && month <= DECEMBER)
                    nextDate = getDate(1, JANUARY, year + 1);
                break;
            case YEAR:
                nextDate = getDate(1, JANUARY, year + 1);
                break;
        }

        return nextDate;
    }

    public static Date getPreviousDate(Date date, FrequencyType frequency) {
        Integer month = Integer.parseInt(getMonthAsString(date));
        Integer year = Integer.parseInt(getYearAsString(date));
        Date previousDate = null;

        switch (frequency) {
            case MONTH:
                if (month == JANUARY)
                    previousDate = getDate(1, DECEMBER, year - 1);
                else
                    previousDate = getDate(1, month - 1, year);
                break;
            case QUARTER:
                if (month >= JANUARY && month <= MARCH)
                    previousDate = getDate(1, OCTOBER, year - 1);
                else if (month >= APRIL && month <= JUNE)
                    previousDate = getDate(1, JANUARY, year);
                else if (month >= JULY && month <= SEPTEMBER)
                    previousDate = getDate(1, APRIL, year);
                else if (month >= OCTOBER && month <= DECEMBER)
                    previousDate = getDate(1, JULY, year);
                break;
            case SEMESTER:
                if (month >= JANUARY && month <= JUNE)
                    previousDate = getDate(1, JULY, year - 1);
                else if (month >= JULY && month <= DECEMBER)
                    previousDate = getDate(1, JANUARY, year);
                break;
            case YEAR:
                previousDate = getDate(1, JANUARY, year - 1);
                break;
        }

        return previousDate;
    }

}
