package com.kb.web.util;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mysql.cj.jdbc.exceptions.SQLError;

public class DbConnectionPoolHandler {

	private static DbConnectionPoolHandler instance;
	
	private BasicDataSource dbConnectionPool;
	
	private DbConnectionPoolHandler() {
		initializeDBConnection();
	}
	
	public Connection getConnection() throws SQLException{
		return dbConnectionPool.getConnection();
	}
	
	public static DbConnectionPoolHandler getInstance() {
		synchronized (DbConnectionPoolHandler.class) {
			if(instance == null) {
				instance = new DbConnectionPoolHandler();
			}
			
		}
		return instance;
	}
	
	private void initializeDBConnection() {
		try {
			dbConnectionPool = new BasicDataSource();
			dbConnectionPool.setUrl(PropertyFileReader.getProperty(ApplicationConstants.JDBC_URL));
			dbConnectionPool.setDriverClassName(PropertyFileReader.getProperty(ApplicationConstants.MYSQL_JDBC_DRIVER_CLASS));
			dbConnectionPool.setUsername(PropertyFileReader.getProperty(ApplicationConstants.DB_USERNAME));
			dbConnectionPool.setPassword(PropertyFileReader.getProperty(ApplicationConstants.DB_PASSWORD));
			dbConnectionPool.setInitialSize(Integer.parseInt(PropertyFileReader.getProperty(ApplicationConstants.DBCP_INITIAL_SIZE)));
			dbConnectionPool.setMinIdle(Integer.parseInt(PropertyFileReader.getProperty(ApplicationConstants.DBCP_MIN_IDLE)));
			dbConnectionPool.setMaxIdle(Integer.parseInt(PropertyFileReader.getProperty(ApplicationConstants.DBCP_MAX_IDLE)));
			dbConnectionPool.getMaxOpenPreparedStatements();
			dbConnectionPool.setMaxWaitMillis(10000);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
