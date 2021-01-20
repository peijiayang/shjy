package tech.binaryer.shjy.web.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description: 数据库配置
 * @create: 2020-08-12 09:36
 **/
@Configuration
@MapperScan(value = {"tech.binaryer.shjy.biz.repository"})
public class ShjyDataSourceConfig {

    @Bean(name = "shjyDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource cabinetDataSourceProperties() {
        return new DruidDataSource();
    }

    /**
     * dataSourceTransactionManager
     *
     * @param dataSource 数据源
     */
    @Bean(name = "shjyTransactionManager")
    @Primary
    public DataSourceTransactionManager dingTransactionManager(@Qualifier("shjyDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
