package com.micro.system.manage.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Noageir
 * Date:2018-05-13 8:48
 * Project:com.spring.cloud
 * Package:com.micro.system.manage.config
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${druid.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "druid.master")
    public DataSource masterDataSource() {
        DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        logger.info("-----------------主库数据信息-------------------");
        logger.info("{}", masterDataSource.toString());
        logger.info("-----------------主库数据信息-------------------");
        return masterDataSource;
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "druid.slave")
    public DataSource slaveDataSource() {
        DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        logger.info("-----------------从库数据信息-------------------");
        logger.info("{}", slaveDataSource.toString());
        logger.info("-----------------从库数据信息-------------------");
        return slaveDataSource;
    }
}
