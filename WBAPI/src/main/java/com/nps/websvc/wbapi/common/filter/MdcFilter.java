/*
 * @(#)MdcFilter.java 2016. 7. 6.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.common.filter;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
* @desc logback 에 저장할 데이터를 mdc에 저장하는 필터
* 
* @author	jkh120
* @date		2016. 7. 6.
* @modify
*/
@Component
public class MdcFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String hostName = "error";
        String remoteAddress = "error";

        InetAddress inetAddress;
        inetAddress = InetAddress.getLocalHost();
        hostName = inetAddress.getHostName();
        
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        remoteAddress = httpRequest.getHeader("X-FORWARDED-FOR");
        if (remoteAddress == null) {
            remoteAddress = request.getRemoteAddr();    
        }

        MDC.put("hostName", hostName);
        MDC.put("remoteAddress", remoteAddress);

        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }

}
