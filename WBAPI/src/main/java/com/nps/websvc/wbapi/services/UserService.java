/*
 * @(#)UserService.java 2016. 7. 4.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nps.websvc.wbapi.common.cache.WbApiRedisCacheTemplate;
import com.nps.websvc.wbapi.common.util.DateUtil;
import com.nps.websvc.wbapi.domain.NwAccessToken;
import com.nps.websvc.wbapi.domain.User;

/**
 * @desc
 * 
 * @author jkh120
 * @date 2016. 7. 4.
 * @modify
 */
@Service
public class UserService {

    @Autowired
    private GpmService gpmService;

    @Autowired
    private WbApiRedisCacheTemplate wbapiRedisTemplate;
    
    @Autowired
    private RedisTemplate<String, String> klpRedisTemplate;
    
    
    public User getUser(){
        NwAccessToken nwAccessToken = getNwAccessTokenFromCookie();
        
        return getUser(nwAccessToken.getNwAccessToken());
    }

    public User getUser(String accessToken){
        User user = null;
        
        if(accessToken != null){
            user = (User) wbapiRedisTemplate.get(accessToken);
        }

        return user;
    }

    public User getUserFromGpm() {
        User user = getUser();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        params.put("userid", user.getUserId());
        result = gpmService.get("Account/getAccount", params);

        return toUser(result);
    }
    
    public User toUser(Map<String, Object> result) {
        int usn = 0;
        String userId = null;
        String displayName = null;
        String profileImgUrl = null;
        String lastSigninDate = null;
        
        usn = Integer.parseInt((String) result.get("account_srl"));
        userId = (String) result.get("userid");
        displayName = (String) result.get("display_name");
        
        // TODO 피망플러스 api연동
        profileImgUrl = "";
        
        Calendar sCal = DateUtil.getCalendar((String) result.get("last_signin_date"));
        lastSigninDate = DateUtil.toFormat(sCal, "yyyy-MM-dd HH:mm:ss");

        User user = new User(usn, userId, displayName, lastSigninDate, profileImgUrl);
        // TODO 유저정보 확인시 캐싱 갱신할지 결정 필요
        
        return user;
    }
    
    
    public boolean existsNwAccessTokenFromCache(NwAccessToken nwAccessToken) {
        if (wbapiRedisTemplate.exists(nwAccessToken.getNwAccessToken())){
            return true;
        }
        
        return false;
    }
    
    public void setNwAcceeToken(NwAccessToken nwAccessToken, User user){
        wbapiRedisTemplate.set(nwAccessToken.getNwAccessToken(), user, 3600);
    }
    
    public NwAccessToken getNwAccessTokenFromCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
    
        NwAccessToken nwAccessToken = new NwAccessToken();
        
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(GpmService.CACHE_ACCESS_TOKEN)) {
                nwAccessToken.setNwAccessToken(cookie.getValue());
            }
            
            if (cookie.getName().equals(GpmService.CACHE_SSID)) {
                nwAccessToken.setNwSsid(cookie.getValue());
            }
        }
        
        return nwAccessToken;
    }

    public int getCurrentUserCnt() {
        int currentUserCnt = 0;
        currentUserCnt = Integer.parseInt((String) klpRedisTemplate.opsForValue().get("idq.klp.cu.156"));
        return currentUserCnt;
    }

}
