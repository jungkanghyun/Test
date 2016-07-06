/*
 * @(#)GameUserService.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nps.websvc.wbapi.domain.GameUser;
import com.nps.websvc.wbapi.domain.AssetLimit;
import com.nps.websvc.wbapi.repositories.GameUserMapper;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@Service
public class GameUserService {
    
    private static final String LIMIT_END_DATE_ORIGIN = "99991231235959"; 
    private static final String LIMIT_END_DATE_REPLACE = "19000101000000";

    @Autowired
    private GameUserMapper gameUserMapper;

    public GameUser getGameUser(int usn) {
        return gameUserMapper.selectOne(usn);
    }
    

    public AssetLimit getMoneyLimitInfo(int usn) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("usn", usn);
        map.put("msn", 2);
        gameUserMapper.getMoneyLimitInfo(map);
        
        AssetLimit moneyLimit = new AssetLimit((Long) map.get("maxMoney"), (Long) map.get("maxSafeMoney"));
        
        return moneyLimit;
    }
    
    public AssetLimit getSubMoneyLimitInfo(int usn) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("usn", usn);
        map.put("msn", 2);
        gameUserMapper.getSubMoneyLimitInfo(map);
        
        AssetLimit pokerchipLimit = new AssetLimit((Long) map.get("ownSubmoneyLimit"), (Long) map.get("safeSubmoneyLimit"));
        
        return pokerchipLimit;
    }
    
    private String toLimitEndDate(String endDate) {
        return endDate.replace(GameUserService.LIMIT_END_DATE_ORIGIN, GameUserService.LIMIT_END_DATE_REPLACE);
    }


}
