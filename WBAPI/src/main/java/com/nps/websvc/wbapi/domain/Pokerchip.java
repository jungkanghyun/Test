/*
 * @(#)Pokerchip.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.domain;

import java.util.Calendar;

import com.nps.websvc.wbapi.common.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

/**
 * @desc Æ÷Ä¿Ä¨
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
public class Pokerchip extends Asset {

    @Getter
    @Setter
    private Long walletValue;

    public Pokerchip() {
        super();
    }

    public Pokerchip(Long value, Long extValue, String extEndDate, Long walletValue) {
        super(value, extValue, extEndDate);
        this.walletValue = walletValue;
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
