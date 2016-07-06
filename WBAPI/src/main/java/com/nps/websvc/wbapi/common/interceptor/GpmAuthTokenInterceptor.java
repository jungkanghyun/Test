/*
 * @(#)GpmAuthTokenInterceptor.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nps.websvc.wbapi.domain.NwAccessToken;
import com.nps.websvc.wbapi.domain.User;
import com.nps.websvc.wbapi.services.GpmService;
import com.nps.websvc.wbapi.services.UserService;

/**
 * @desc nw_access_token 과 ssid 쿠키값으로 api인증 체크
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public class GpmAuthTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private GpmService gpmService;
    
    @Autowired
    private UserService userService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String accessToken = null;
        String ssid = null;
        
        NwAccessToken nwAccessToken = userService.getNwAccessTokenFromCookie();
        accessToken = nwAccessToken.getNwAccessToken();
        ssid = nwAccessToken.getNwSsid();

        // cache 확인 후 존재할 경우 정상 인증으로 처리
        if (accessToken != null){
            if (userService.existsNwAccessTokenFromCache(nwAccessToken)){
                return true;
            }
        }

        Map<String, Object> me = null;
        
        if (accessToken != null && ssid != null) {
            me = gpmService.me(false);
        }

        if (me != null && (boolean) me.get("is_signin") == true ) {
            User user = userService.toUser(me);
            userService.setNwAcceeToken(nwAccessToken, user);
            return true;
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
