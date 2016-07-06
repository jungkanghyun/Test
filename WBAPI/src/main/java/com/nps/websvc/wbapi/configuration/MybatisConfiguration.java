package com.nps.websvc.wbapi.configuration;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.nps.websvc.wbapi.common.annotation.Saycashweb;
import com.nps.websvc.wbapi.common.annotation.Saygame;
import com.nps.websvc.wbapi.common.annotation.Saymtpk;
import com.nps.websvc.wbapi.common.annotation.Saypcb;

/**
 * @desc mybatis ¼³Á¤ Ãß»ó Å¬·¡½º
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
public abstract class MybatisConfiguration {

    public static final String BASE_PACKAGE = "com.nps.websvc.wbapi.repositories";
    public static final String TYPE_ALIASES_PACKAGE = "com.nps.websvc.wbapi.domain";
    public static final String CONFIG_LOCATION_PATH = "classpath:mybatis/config/mybatis-config.xml";
    public static final String MAPPER_LOCATIONS_PATH = "classpath:mybatis/mapper/*.xml";

    protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource)
            throws IOException {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
        sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
    }
}

/**
 * @desc saygame mybatis ¼³Á¤ºó
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
@MapperScan(basePackages = MybatisConfiguration.BASE_PACKAGE, annotationClass = Saygame.class, sqlSessionFactoryRef = "saygameSqlSessionFactory")
class SaygameMyBatisConfiguration extends MybatisConfiguration {

    @Primary
    @Bean(name = "saygameSqlSessionFactory")
    public SqlSessionFactory saygameSqlSessionFactory(@Qualifier("saygameDataSource") DataSource saygameDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, saygameDataSource);
        return sessionFactoryBean.getObject();
    }
}

/**
 * @desc saycashweb mybatis ¼³Á¤ºó
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
@MapperScan(basePackages = MybatisConfiguration.BASE_PACKAGE, annotationClass = Saycashweb.class, sqlSessionFactoryRef = "saycashwebSqlSessionFactory")
class SaycashwebMyBatisConfiguration extends MybatisConfiguration {

    @Bean(name = "saycashwebSqlSessionFactory")
    public SqlSessionFactory saycashwebSqlSessionFactory(
            @Qualifier("saycashwebDataSource") DataSource saycashwebDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, saycashwebDataSource);
        return sessionFactoryBean.getObject();
    }
}

/**
 * @desc saypcb mybatis ¼³Á¤ºó
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
@MapperScan(basePackages = MybatisConfiguration.BASE_PACKAGE, annotationClass = Saypcb.class, sqlSessionFactoryRef = "saypcbSqlSessionFactory")
class SaypcbMyBatisConfiguration extends MybatisConfiguration {

    @Bean(name = "saypcbSqlSessionFactory")
    public SqlSessionFactory saypcbSqlSessionFactory(@Qualifier("saypcbDataSource") DataSource saypcbDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, saypcbDataSource);
        return sessionFactoryBean.getObject();
    }
}

/**
 * @desc saypcb mybatis ¼³Á¤ºó
 * 
 * @author jkh120
 * @date 2016. 7. 1.
 * @modify
 */
@Configuration
@MapperScan(basePackages = MybatisConfiguration.BASE_PACKAGE, annotationClass = Saymtpk.class, sqlSessionFactoryRef = "saymtpkSqlSessionFactory")
class SaymtpkMyBatisConfiguration extends MybatisConfiguration {

    @Bean(name = "saymtpkSqlSessionFactory")
    public SqlSessionFactory saymtpkSqlSessionFactory(@Qualifier("saymtpkDataSource") DataSource saymtpkDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        configureSqlSessionFactory(sessionFactoryBean, saymtpkDataSource);
        return sessionFactoryBean.getObject();
    }
}
