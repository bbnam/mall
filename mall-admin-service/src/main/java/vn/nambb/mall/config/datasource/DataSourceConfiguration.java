package vn.nambb.mall.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import vn.nambb.mall.common.contant.DataSourceConstant;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = DataSourceConstant.BASE_PACKAGES,
        entityManagerFactoryRef = "primaryEntityManagerFactory")
public class DataSourceConfiguration {
    private final DataSourceConfig dataSourceConfig;

    public DataSourceConfiguration(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }


    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        dataSource.setJdbcUrl(dataSourceConfig.getUrl());
        dataSource.setDriverClassName(dataSourceConfig.getDriverClassName());
        dataSource.setMaximumPoolSize(dataSourceConfig.getHikari().getMaximumPoolSize());
        dataSource.setMinimumIdle(dataSourceConfig.getHikari().getMinimumIdle());
        dataSource.setConnectionTimeout(dataSourceConfig.getHikari().getConnectionTimeout());
        dataSource.setIdleTimeout(dataSourceConfig.getHikari().getIdleTimeout());
        dataSource.setMaxLifetime(dataSourceConfig.getHikari().getMaxLifetime());
        dataSource.setPoolName(dataSourceConfig.getHikari().getPoolName());
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        Map<String, String> properties = new HashMap<>();
        properties.put(DataSourceConstant.HIBERNATE_PHYSICAL_NAMING_STRATEGY, DataSourceConstant.HIBERNATE_PHYSICAL_NAMING_STRATEGY_VALUE);
        properties.put(DataSourceConstant.HIBERNATE_DIALECT, DataSourceConstant.HIBERNATE_DIALECT_VALUE);
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(DataSourceConstant.BASE_PACKAGES);
        em.setJpaVendorAdapter(vendorAdapter);
        em.getJpaPropertyMap().putAll(properties);
        return em;
    }


}
