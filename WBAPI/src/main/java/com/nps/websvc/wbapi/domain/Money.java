/*
 * @(#)Money.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import java.util.Calendar;

import com.nps.websvc.wbapi.common.util.DateUtil;

/**
 * @desc Money
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class Money extends Asset {
    
    public Money() {
        super();
    }

    public Money(Long value, Long extValue, String extEndDate) {
        super(value, extValue, extEndDate);
    }

    @Override
    public void doValidValue() {
        Calendar cal = DateUtil.getCalendar(getExtEndDate());
        int result = cal.compareTo(Calendar.getInstance());

        if (result < 0) {
            setExtValue(Asset.DEFAULT_EXT_VALUE);
        }
    }
}
