/*
 * @(#)SaymtpkDatabaseProperties.java 2016. 7. 1.
 *
 * Copyright 2016 NeowizPlayStudio. All rights Reserved.
 */
package com.nps.websvc.wbapi.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @desc saymtpk Datasource properties ¼ÂÆÃ
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
@ConfigurationProperties
public class SaymtpkDatabaseProperties implements DatabaseProperties {

    @Value("${crypto.host}")
    private String cryptoHost;

    @Value("${saymtpk.driver-class-name}")
    private String driverClassName;

    @Value("${saymtpk.url}")
    private String url;

    @Value("${saymtpk.username}")
    private String userName;

    @Value("${saymtpk.password}")
    private String password;

    @Value("${saymtpk.initial-size}")
    private int initialSize;

    @Value("${saymtpk.max-active}")
    private int maxActive;

    @Value("${saymtpk.max-idle}")
    private int maxIdle;

    @Value("${saymtpk.min-idle}")
    private int minIdle;

    @Value("${saymtpk.max-wait}")
    private int maxWait;

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getInitialSize() {
        return initialSize;
    }

    @Override
    public int getMaxActive() {
        return maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public int getMaxWait() {
        return maxWait;
    }

    @Override
    public String getCryptoHost() {
        return cryptoHost;
    }

}
