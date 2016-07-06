package com.nps.websvc.wbapi.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @desc saygame Datasource properties ¼ÂÆÃ
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
@ConfigurationProperties
public class SaygameDatabaseProperties implements DatabaseProperties {

    @Value("${crypto.host}")
    private String cryptoHost;

    @Value("${saygame.driver-class-name}")
    private String driverClassName;

    @Value("${saygame.url}")
    private String url;

    @Value("${saygame.username}")
    private String userName;

    @Value("${saygame.password}")
    private String password;

    @Value("${saygame.initial-size}")
    private int initialSize;

    @Value("${saygame.max-active}")
    private int maxActive;

    @Value("${saygame.max-idle}")
    private int maxIdle;

    @Value("${saygame.min-idle}")
    private int minIdle;

    @Value("${saygame.max-wait}")
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
