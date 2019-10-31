package com.shoppingcart.beans;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.context.annotation.Bean;

public class DataSource {
	@Bean
	public org.apache.tomcat.jdbc.pool.DataSource getDataSource() {

		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		PoolProperties p = new PoolProperties();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:harjun250");
		ds.setUsername("system");
		ds.setPassword("Atram8585");
        p.setUrl("jdbc:oracle:thin:@localhost:1521:harjun250");
        p.setDriverClassName("oracle.jdbc.OracleDriver");
        p.setUsername("system");
        p.setPassword("Atram8585");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery(" SELECT 1 FROM DUAL");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        ds.setPoolProperties(p);
        return ds;

	}
}
