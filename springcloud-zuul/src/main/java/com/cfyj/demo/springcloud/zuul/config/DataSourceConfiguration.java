package com.cfyj.demo.springcloud.zuul.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.SQLException;

/**
 * @author GJ
 * @create 2018-04-11 15:55
 * 数据库连接池配置
 */
@Slf4j
@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;
    
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Bean
    @Primary
    public DruidDataSource dataSource() throws SQLException {
        log.info("url=" + url);
        log.info("driverClassName=" + driverClassName);
        log.info("initialSize=" + initialSize);
        log.info("minIdle=" + minIdle);
        log.info("maxActive=" + maxActive);
        log.info("maxWait=" + maxWait);
        log.info("timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis);
        log.info("minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis);
        log.info("validationQuery=" + validationQuery);
        log.info("testWhileIdle=" + testWhileIdle);
        log.info("testOnBorrow=" + testOnBorrow);
        log.info("testOnReturn=" + testOnReturn);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setFilters(filters);
        dataSource.setConnectionProperties(connectionProperties);
        
        /**
         * oracle 打开PSCache，并且指定每个连接上PSCache的大小 : 是否缓存preparedStatement，也就是PSCache。 
        	PSCache对支持游标的数据库性能提升巨大，比如说oracle,要启用PSCache，必须配置大于0，当大于0时， poolPreparedStatements自动触发修改为true。 
			在Druid中，不会存在Oracle下PSCache占用内存过多的问题， 可以把这个数值配置大一些，比如说100
         **/
//        dataSource.setPoolPreparedStatements(poolPreparedStatements);
//        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
  
        
        //开启监控
        //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        /**
         * 1.Druid内置提供一个StatFilter，用于统计监控信息，stat指定使用的是statFilter
         * stat:statFilter
         * mergeStat : statFilter+ mergeSql功能 ，也可以从connectionProperties 指定 druid.stat.mergeSql=true
         * wall 防SQL注入，可以配置 拦截哪些类型的SQL
         * Druid内置提供了四种LogFilter： log4j、log4j2、slf4j、commonlogging
         * 2.connectionProperties: 
         * 	druid.stat.slowSqlMillis=3000 指定慢Sql的限定时间
         * 	config.decrypt=false   配置druid进行数据库密码解密
         * 	druid.stat.mergeSql=true   合并无参SQL
         */
//        dataSource.setFilters(filters+",stat,slf4j"); 
//        dataSource.setConnectionProperties(connectionProperties+";druid.stat.mergeSql=true;druid.stat.slowSqlMillis=1000");    
        //合并多个DruidDataSource的监控数据
//        dataSource.setUseGlobalDataSourceStat(true);
 
        return dataSource;
    }

}
