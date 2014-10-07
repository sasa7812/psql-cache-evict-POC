package ru.savvy.config;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sasa <a href="mailto:sasa7812@gmail.com">Alexander Nikitin</a>
 */

@Configuration
@EnableTransactionManagement
//@EnableJpaRepositories("ru.savvy.repository")
public class SpringConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        localContainerEntityManagerFactoryBean.setJpaDialect(new EclipseLinkJpaDialect());
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new EclipseLinkJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.getJpaPropertyMap().put(PersistenceUnitProperties.WEAVING_CHANGE_TRACKING, "true");
        localContainerEntityManagerFactoryBean.getJpaPropertyMap().put(PersistenceUnitProperties.DDL_GENERATION, "create-or-extend-tables");
        localContainerEntityManagerFactoryBean.getJpaPropertyMap().put(PersistenceUnitProperties.WEAVING, "false");
        localContainerEntityManagerFactoryBean.getJpaPropertyMap().put(PersistenceUnitProperties.LOGGING_LEVEL, "FINE");

        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
