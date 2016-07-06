package com.nps.websvc.wbapi.configuration.properties;

/**
 * @desc Datasource properties 인터페이스
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public interface DatabaseProperties {

    public String getCryptoHost();

    public String getDriverClassName();

    public String getUrl();

    public String getUserName();

    public String getPassword();

    public int getInitialSize();

    public int getMaxActive();

    public int getMaxIdle();

    public int getMinIdle();

    public int getMaxWait();

}
