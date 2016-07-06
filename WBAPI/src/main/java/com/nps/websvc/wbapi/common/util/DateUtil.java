/*
 * @(#)DateUtil.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class DateUtil {

    public static Calendar getCalendar(String str) {
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(4, 6)) - 1,
                Integer.parseInt(str.substring(6, 8)), Integer.parseInt(str.substring(8, 10)),
                Integer.parseInt(str.substring(10, 12)), Integer.parseInt(str.substring(12, 14)));
        return cal;
    }
    
    public static String toFormat(Calendar cal, String format) {
        DateFormat formatter;
        formatter = new SimpleDateFormat(format);
        
        return formatter.format(new Date(cal.getTimeInMillis()));
    }
    
    public static int getExpiredTimeToday() {
        Calendar sCal = Calendar.getInstance();
        Calendar eCal = Calendar.getInstance();
        eCal.add(Calendar.DATE, 1);
        eCal = DateUtil.getCalendar(DateUtil.toFormat(eCal, "yyyyMMdd"+"000000"));
        long expireTime = (eCal.getTimeInMillis() - sCal.getTimeInMillis()) / 1000;
        
        return (int) expireTime;
    }
}
