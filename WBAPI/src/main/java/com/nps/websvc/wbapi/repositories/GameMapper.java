/*
 * @(#)GameMapper.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.repositories;

import java.util.ArrayList;
import java.util.HashMap;

import com.nps.websvc.wbapi.common.annotation.Saymtpk;
import com.nps.websvc.wbapi.domain.Jackpot;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */

@Saymtpk
public interface GameMapper {
    public ArrayList<Jackpot> getTotalJackpot(HashMap<String, Object> map);

    public ArrayList<Jackpot> getTotalJackpotByGameInfoSrl(HashMap<String, Object> map);

    public ArrayList<Jackpot> getYesterdayJackpot(HashMap<String, Object> map);

    public ArrayList<Jackpot> getYesterdayJackpotByGameInfoSrl(HashMap<String, Object> map);
}
