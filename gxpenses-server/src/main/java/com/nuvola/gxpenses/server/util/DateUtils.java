package com.nuvola.gxpenses.server.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.nuvola.gxpenses.common.shared.type.FrequencyType;
import com.nuvola.gxpenses.common.shared.type.PeriodType;

public class DateUtils {

    public static Date getFirstDayMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

        return calendar.getTime();
    }

    public static Date getLastDayMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));

        return calendar.getTime();
    }

    public static Date getFirstDayQuarter(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Integer month = calendar.get(Calendar.MONTH);

        if (month >= Calendar.JANUARY && month <= Calendar.MARCH)
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
        else if (month >= Calendar.APRIL && month <= Calendar.JUNE)
            calendar.set(Calendar.MONTH, Calendar.APRIL);
        else if (month >= Calendar.JULY && month <= Calendar.SEPTEMBER)
            calendar.set(Calendar.MONTH, Calendar.JULY);
        else if (month >= Calendar.OCTOBER && month <= Calendar.DECEMBER)
            calendar.set(Calendar.MONTH, Calendar.OCTOBER);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        return calendar.getTime();
    }

    public static Date getFirstDaySemester(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Integer month = calendar.get(Calendar.MONTH);

        if (month >= Calendar.JANUARY && month <= Calendar.JUNE)
            calendar.set(Calendar.MONTH, Calendar.JANUARY);
        else if (month >= Calendar.JULY && month <= Calendar.DECEMBER)
            calendar.set(Calendar.MONTH, Calendar.JULY);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        return calendar.getTime();
    }

    public static Date getLastDayQuarter(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Integer month = calendar.get(Calendar.MONTH);

        if (month >= Calendar.JANUARY && month <= Calendar.MARCH)
            calendar.set(Calendar.MONTH, Calendar.MARCH);
        else if (month >= Calendar.APRIL && month <= Calendar.JUNE)
            calendar.set(Calendar.MONTH, Calendar.JUNE);
        else if (month >= Calendar.JULY && month <= Calendar.SEPTEMBER)
            calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        else if (month >= Calendar.OCTOBER && month <= Calendar.DECEMBER)
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        return calendar.getTime();
    }

    public static Date getLastDaySemester(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Integer month = calendar.get(Calendar.MONTH);

        if (month >= Calendar.JANUARY && month <= Calendar.JUNE)
            calendar.set(Calendar.MONTH, Calendar.JUNE);
        else if (month >= Calendar.JULY && month <= Calendar.DECEMBER)
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        return calendar.getTime();
    }

    public static Date getFirstDayYear(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));

        return calendar.getTime();
    }

    public static Date getLastDayYear(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));

        return calendar.getTime();
    }

    public static Date getStartDate(PeriodType periode, Date now) {
        Calendar calendar = null;

        switch (periode) {
            case LAST_MONTH:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                    calendar.set(Calendar.MONTH, Calendar.DECEMBER);
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                } else
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                return getFirstDayMonth(calendar.getTime());

            case THIS_MONTH:
                return getFirstDayMonth(now);

            case THIS_QUARTER:
                return getFirstDayQuarter(now);

            case LAST_QUARTER:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                Integer month = calendar.get(Calendar.MONTH);
                if (month >= Calendar.JANUARY && month <= Calendar.MARCH) {
                    calendar.set(Calendar.MONTH, Calendar.OCTOBER);
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                } else if (month >= Calendar.APRIL && month <= Calendar.JUNE) {
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                } else if (month >= Calendar.JULY && month <= Calendar.SEPTEMBER) {
                    calendar.set(Calendar.MONTH, Calendar.APRIL);
                } else if (month >= Calendar.OCTOBER && month <= Calendar.DECEMBER) {
                    calendar.set(Calendar.MONTH, Calendar.JULY);
                }
                return getFirstDayQuarter(calendar.getTime());

            case THIS_SEMESTER:
                return getFirstDaySemester(now);

            case LAST_SEMESTER:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                Integer month1 = calendar.get(Calendar.MONTH);
                if (month1 >= Calendar.JANUARY && month1 <= Calendar.JUNE) {
                    calendar.set(Calendar.MONTH, Calendar.JULY);
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                } else if (month1 >= Calendar.JULY && month1 <= Calendar.DECEMBER) {
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                }
                return getFirstDaySemester(calendar.getTime());

            case THIS_YEAR:
                return getFirstDayYear(now);

            case LAST_YEAR:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                return getFirstDayYear(calendar.getTime());
        }

        return now;
    }

    public static Date getEndDate(PeriodType periode, Date now) {
        Calendar calendar = null;

        switch (periode) {
            case LAST_MONTH:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
                    calendar.set(Calendar.MONTH, Calendar.DECEMBER);
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                } else
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                return getLastDayMonth(calendar.getTime());

            case THIS_MONTH:
                return getLastDayMonth(now);

            case THIS_QUARTER:
                return getLastDayQuarter(now);

            case LAST_QUARTER:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                Integer month = calendar.get(Calendar.MONTH);
                if (month >= Calendar.JANUARY && month <= Calendar.MARCH) {
                    calendar.set(Calendar.MONTH, Calendar.OCTOBER);
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                } else if (month >= Calendar.APRIL && month <= Calendar.JUNE) {
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                } else if (month >= Calendar.JULY && month <= Calendar.SEPTEMBER) {
                    calendar.set(Calendar.MONTH, Calendar.APRIL);
                } else if (month >= Calendar.OCTOBER && month <= Calendar.DECEMBER) {
                    calendar.set(Calendar.MONTH, Calendar.JULY);
                }
                return getLastDayQuarter(calendar.getTime());

            case THIS_SEMESTER:
                return getLastDaySemester(now);

            case LAST_SEMESTER:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                Integer month1 = calendar.get(Calendar.MONTH);
                if (month1 >= Calendar.JANUARY && month1 <= Calendar.JUNE) {
                    calendar.set(Calendar.MONTH, Calendar.JULY);
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                } else if (month1 >= Calendar.JULY && month1 <= Calendar.DECEMBER) {
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                }
                return getLastDaySemester(calendar.getTime());

            case THIS_YEAR:
                return getLastDayYear(now);

            case LAST_YEAR:
                calendar = GregorianCalendar.getInstance();
                calendar.setTime(now);
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
                return getLastDayYear(calendar.getTime());
        }

        return now;
    }

    public static Date getStartDateFrequency(FrequencyType frequencyType, Date date) {
        switch (frequencyType) {
            case MONTH:
                return getFirstDayMonth(date);

            case QUARTER:
                return getFirstDayQuarter(date);

            case SEMESTER:
                return getFirstDaySemester(date);

            case YEAR:
                return getFirstDayYear(date);
        }

        return date;
    }

    public static Date getLastDateFrequency(FrequencyType frequencyType, Date date) {
        switch (frequencyType) {
            case MONTH:
                return getLastDayMonth(date);

            case QUARTER:
                return getLastDayQuarter(date);

            case SEMESTER:
                return getLastDaySemester(date);

            case YEAR:
                return getLastDayYear(date);
        }

        return date;
    }
}
