package com.lstfight.dao.config;

import com.lstfight.dao.dao.RepositoryFactoryBean;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.mapping.JpaPersistentProperty;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author 李尚庭
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.lstfight.*",
        transactionManagerRef = "jpaTransactionManager",repositoryFactoryBeanClass = RepositoryFactoryBean.class)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement()
public class DaoConfig {

    /**
     * 通过注解导入的properties持有对象
     */
    @Autowired
    Environment environment;

    private static final String DEFAULT_MODE = "mysql";

    @Value("${mode}")
    private String mode;

    @Value("${default.oracle.driver}")
    private String driverName;

    @Value("${default.mysql.driver}")
    private String mysqlDriverName;

    @Value("${default.oracle.url}")
    private String url;

    @Value("${default.mysql.url}")
    private String mysqlUrl;

    @Value("${default.jdbc.username}")
    private String userName;

    @Value("${default.jdbc.password}")
    private String password;

    @Value("${default.dbcp2.maxIdle}")
    private int maxIdle;

    @Value("${default.dbcp2.maxActive}")
    private int maxActive;

    /**
     * 配置dbcp数据源
     *
     * @return 返回数据源
     */
    @Bean(name = "dataSource")
    DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        if (DEFAULT_MODE.equals(mode) || null == mode) {
            url = mysqlUrl;
            driverName = mysqlDriverName;
        }
        dataSource.setDriverClassName(mysqlDriverName);
        dataSource.setUrl(mysqlUrl);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMaxTotal(maxActive);
        dataSource.setDefaultAutoCommit(false);
        //设置连接超时
        dataSource.setTimeBetweenEvictionRunsMillis(1000);
        dataSource.setMinEvictableIdleTimeMillis(1000);
        return dataSource;
    }

    /**
     * 指定jap到hibernate的适配器
     *
     * @return 适配器
     */
    @Bean(name = "jpaVendorAdapter")
   JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabasePlatform(environment.getProperty("default.jpa.database-platform"));
        return hibernateJpaVendorAdapter;
    }

    /**
     * 配置实体管理工厂
     *
     * @return 实体管理工厂
     */
    @Bean(name = "entityManagerFactory")
    AbstractEntityManagerFactoryBean entityManager() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.lstfight.*.entity");
        //设置Jpa属性
        entityManagerFactoryBean.setJpaProperties(new Properties());
        return entityManagerFactoryBean;
    }

    /**
     * 配置事务管理器 事务策略的配置
     */
    @Bean(name = "jpaTransactionManager")
    PlatformTransactionManager jpaTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        return jpaTransactionManager;
    }

    /**
     * 配置jdbcTemplate
     *
     * @return jdbcTemplate
     */
    @Bean(name = "jdbcTemplate")
    JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getDataSource());
        return jdbcTemplate;
    }
}

