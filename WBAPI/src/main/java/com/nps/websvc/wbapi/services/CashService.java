/*
 * @(#)CashService.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nps.websvc.wbapi.domain.Cash;
import com.nps.websvc.wbapi.repositories.CashMapper;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@Service
public class CashService {

    @Autowired
    private CashMapper cashMapper;

    public Cash getCash(int usn) {
        String sUsn = Integer.toString(usn);
        return cashMapper.selectOne(sUsn);
    }

}
