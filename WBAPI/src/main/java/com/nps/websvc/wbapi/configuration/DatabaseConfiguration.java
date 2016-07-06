package com.nps.websvc.wbapi.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.neowiz.crypto.nwzCrypto;
import com.neowiz.crypto.nwzCryptoException;
import com.nps.websvc.wbapi.configuration.properties.DatabaseProperties;
import com.nps.websvc.wbapi.configuration.properties.SaycashwebDatabaseProperties;
import com.nps.websvc.wbapi.configuration.properties.SaygameDatabaseProperties;
import com.nps.websvc.wbapi.configuration.properties.SaymtpkDatabaseProperties;
import com.nps.websvc.wbapi.configuration.properties.SaypcbDatabaseProperties;

/**
 * @desc Datasource 추상 클래스
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public abstract class DatabaseConfiguration {

    @Bean
    public abstract DataSource dataSource();

    protected void configureDataSource(DataSource dataSource, DatabaseProperties databaseProperties) {

        dataSource.setDriverClassName(databaseProperties.getDriverClassName());
        dataSource.setUrl(databaseProperties.getUrl());
        dataSource.setMaxActive(databaseProperties.getMaxActive());
        dataSource.setMaxIdle(databaseProperties.getMaxIdle());
        dataSource.setMinIdle(databaseProperties.getMinIdle());
        dataSource.setMaxWait(databaseProperties.getMaxWait());
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        dataSource.setUsername(databaseProperties.getUserName());
        dataSource.setPassword(databaseProperties.getPassword());

        // try {
        // dataSource.setUsername(decrypt(databaseProperties.getCryptoHost(),
        // databaseProperties.getUserName()));
        // dataSource.setPassword(decrypt(databaseProperties.getCryptoHost(),
        // databaseProperties.getPassword()));
        // } catch (nwzCryptoException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    private static String decrypt(String hostname, String key) throws nwzCryptoException {
        if (hostname.isEmpty())
            return key;

        nwzCrypto.initialize();

        String encryptedText;

        if (!hostname.isEmpty()) {
            encryptedText = nwzCrypto.nwzDecryptU(nwzCrypto.QueryToAuthServer(hostname, key), 0)
                    .replaceAll("[^\\x20-\\x7f]", "");

            System.out.println("key : " + encryptedText);
            return encryptedText;
        } else {
            return key;
        }
    }

}

/**
 * @desc saygame Datasource
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
//@EnableTransactionManagement
@EnableConfigurationProperties(SaygameDatabaseProperties.class)
class SaygameDatabaseConfiguration extends DatabaseConfiguration {

    @Autowired
    private SaygameDatabaseProperties saygameDatabaseProperties;

    @Primary
    @Bean(name = "saygameDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource saygameDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(saygameDataSource, saygameDatabaseProperties);
        return saygameDataSource;
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(@Qualifier("saygameDataSource") DataSource saygameDataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(saygameDataSource);
//        transactionManager.setGlobalRollbackOnParticipationFailure(false);
//        return transactionManager;
//    }
}

/**
 * @desc saycashweb Datasource
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
//@EnableTransactionManagement
@EnableConfigurationProperties(SaycashwebDatabaseProperties.class)
class SaycashwebDatabaseConfiguration extends DatabaseConfiguration {

    @Autowired
    private SaycashwebDatabaseProperties saycashwebDatabaseProperties;

    @Bean(name = "saycashwebDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource saycashwebDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(saycashwebDataSource, saycashwebDatabaseProperties);
        return saycashwebDataSource;
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("saycashwebDataSource") DataSource saycashwebDataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(saycashwebDataSource);
//        transactionManager.setGlobalRollbackOnParticipationFailure(false);
//        return transactionManager;
//    }
}

/**
 * @desc saypcb Datasource
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
//@EnableTransactionManagement
@EnableConfigurationProperties(SaypcbDatabaseProperties.class)
class SaypcbDatabaseConfiguration extends DatabaseConfiguration {

    @Autowired
    private SaypcbDatabaseProperties saypcbDatabaseProperties;

    @Bean(name = "saypcbDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource saypcbDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(saypcbDataSource, saypcbDatabaseProperties);
        return saypcbDataSource;
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(@Qualifier("saypcbDataSource") DataSource saypcbDataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(saypcbDataSource);
//        transactionManager.setGlobalRollbackOnParticipationFailure(false);
//        return transactionManager;
//    }
}

/**
 * @desc saymtpk Datasource
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
//@EnableTransactionManagement
@EnableConfigurationProperties(SaymtpkDatabaseProperties.class)
class SaymtpkDatabaseConfiguration extends DatabaseConfiguration {

    @Autowired
    private SaymtpkDatabaseProperties saymtpkDatabaseProperties;

    @Bean(name = "saymtpkDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        org.apache.tomcat.jdbc.pool.DataSource saymtpkDataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        configureDataSource(saymtpkDataSource, saymtpkDatabaseProperties);
        return saymtpkDataSource;
    }

//    @Bean
//    public PlatformTransactionManager transactionManager(@Qualifier("saymtpkDataSource") DataSource saymtpkDataSource) {
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(saymtpkDataSource);
//        transactionManager.setGlobalRollbackOnParticipationFailure(false);
//        return transactionManager;
//    }
}