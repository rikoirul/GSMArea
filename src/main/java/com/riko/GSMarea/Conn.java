package com.riko.GSMarea;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conn {
	public Connection connection = null;
	public PreparedStatement pr = null;
	public ResultSet rs = null;
	public String rt = null;
	public CallableStatement cl = null;

	public void closeConnection() throws Exception {
		try {
			if (connection != null) {
				connection.close();
			}

			if (pr != null) {
				pr.close();
			}

			if (rs != null) {
				rs.close();
			}

			if (cl != null) {
				cl.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeStatement() throws Exception {
		try {
			if (this.pr != null) {
				this.pr.close();
			}

			if (this.rs != null) {
				this.rs.close();
			}

			if (this.cl != null) {
				this.cl.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
