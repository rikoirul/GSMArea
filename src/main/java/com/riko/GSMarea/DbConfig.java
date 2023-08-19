package com.riko.GSMarea;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DbConfig {

	@Autowired
	@Qualifier("datasource")
	private DataSource dataSource;

	private static final Logger logger = LoggerFactory.getLogger(DbConfig.class);

	protected Connection conn = null;

	public void connect() {
		try {
			this.conn = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			if (this.conn == null)
				this.connect();
			else if (this.conn.isClosed())
				this.connect();

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}

		return this.conn;
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

}
