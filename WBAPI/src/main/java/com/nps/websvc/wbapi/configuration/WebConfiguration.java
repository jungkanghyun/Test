/*
 * @(#)WebConfiguration.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nps.websvc.wbapi.common.interceptor.GpmAuthTokenInterceptor;

/**
* @desc web application ¼³Á¤ºó
* 
* @author	jkh120
* @date		2016. 7. 1.
* @modify
*/
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(createGpmAuthTokenInterceptor()).addPathPatterns("/**");
    }
    
    
    @Bean
    public GpmAuthTokenInterceptor createGpmAuthTokenInterceptor() {
        return new GpmAuthTokenInterceptor();
    }
    

}
