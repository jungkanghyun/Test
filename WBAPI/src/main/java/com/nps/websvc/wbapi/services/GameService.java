/*
 * @(#)GameService.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nps.websvc.wbapi.common.cache.WbApiRedisCacheTemplate;
import com.nps.websvc.wbapi.common.util.DateUtil;
import com.nps.websvc.wbapi.domain.Jackpot;
import com.nps.websvc.wbapi.domain.MtpkGame;
import com.nps.websvc.wbapi.repositories.GameMapper;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@Service
public class GameService {

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private WbApiRedisCacheTemplate wbapiRedisTemplate;

    @SuppressWarnings("unchecked")
    public ArrayList<Jackpot> getTotalJackpot() {
        

        ArrayList<Jackpot> jackpot = null;
        String key = "game.jackpot.total.all";

        if (wbapiRedisTemplate.exists(key)) {
            jackpot = (ArrayList<Jackpot>) wbapiRedisTemplate.get(key);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            
            Calendar cal = Calendar.getInstance();

            map.put("startDate", "00000000000000");
            map.put("endDate", DateUtil.toFormat(cal, "yyyyMMdd") + "000000");

            jackpot = gameMapper.getTotalJackpot(map);

            jackpot.forEach((v) -> {
                v.setRegDate(DateUtil.toFormat(DateUtil.getCalendar(v.getRegDate()), "yyyy.MM.dd"));
                v.setGameName(MtpkGame.getName(v.getGameInfoSrl()));
            });
            
            wbapiRedisTemplate.set(key, jackpot, DateUtil.getExpiredTimeToday());
        }

        return jackpot;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Jackpot> getTotalJackpotByGameInfoSrl(int gameInfoSrl) {
        
        ArrayList<Jackpot> jackpot = null;
        String key = "game.jackpot.total." + gameInfoSrl;

        if (wbapiRedisTemplate.exists(key)) {
            jackpot = (ArrayList<Jackpot>) wbapiRedisTemplate.get(key);
        } else {
            HashMap<String, Object> map = new HashMap<>();

            Calendar cal = Calendar.getInstance();

            map.put("startDate", "00000000000000");
            map.put("endDate", DateUtil.toFormat(cal, "yyyyMMdd") + "000000");
            map.put("gameInfoSrl", gameInfoSrl);

            jackpot = gameMapper.getTotalJackpotByGameInfoSrl(map);
            
            jackpot.forEach((v) -> {
                v.setRegDate(DateUtil.toFormat(DateUtil.getCalendar(v.getRegDate()), "yyyy.MM.dd"));
                v.setGameName(MtpkGame.getName(v.getGameInfoSrl()));
            });
            
            wbapiRedisTemplate.set(key, jackpot, DateUtil.getExpiredTimeToday());
        }

        return jackpot;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Jackpot> getYesterdayJackpot() {

        ArrayList<Jackpot> jackpot = null;
        String key = "game.jackpot.yesterday.all";

        if (wbapiRedisTemplate.exists(key)) {
            jackpot = (ArrayList<Jackpot>) wbapiRedisTemplate.get(key);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            
            Calendar cal = Calendar.getInstance();
            map.put("endDate", DateUtil.toFormat(cal, "yyyyMMdd") + "000000");
            cal.add(Calendar.DATE, -1);
            map.put("startDate", DateUtil.toFormat(cal, "yyyyMMdd") + "000000");

            jackpot = gameMapper.getYesterdayJackpot(map);
            
            jackpot.forEach((v) -> {
                    v.setRegDate(DateUtil.toFormat(DateUtil.getCalendar(v.getRegDate()), "yyyy.MM.dd"));
                    v.setGameName(MtpkGame.getName(v.getGameInfoSrl()));
                });

            wbapiRedisTemplate.set(key, jackpot, DateUtil.getExpiredTimeToday());
        }

        return jackpot;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Jackpot> getYesterdayJackpotByGameInfoSrl(int gameInfoSrl) {
        
        ArrayList<Jackpot> jackpot = null;
        String key = "game.jackpot.yesterday." + gameInfoSrl;

        if (wbapiRedisTemplate.exists(key)) {
            jackpot = (ArrayList<Jackpot>) wbapiRedisTemplate.get(key);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            
            Calendar cal = Calendar.getInstance();
            map.put("endDate", DateUtil.toFormat(cal, "yyyyMMdd") + "000000");
            cal.add(Calendar.DATE, -1);
            map.put("startDate", DateUtil.toFormat(cal, "yyyyMMdd") + "000000");          
            map.put("gameInfoSrl", gameInfoSrl);

            jackpot = gameMapper.getYesterdayJackpotByGameInfoSrl(map);

            jackpot.forEach((v) -> {
                v.setRegDate(DateUtil.toFormat(DateUtil.getCalendar(v.getRegDate()), "yyyy.MM.dd"));
                v.setGameName(MtpkGame.getName(v.getGameInfoSrl()));
            });
            
            wbapiRedisTemplate.set(key, jackpot, DateUtil.getExpiredTimeToday());
        }

        return jackpot;
    }
    
    public String getJackpotLastUpdate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return DateUtil.toFormat(cal, "yyyy.MM.dd");
    }

}
