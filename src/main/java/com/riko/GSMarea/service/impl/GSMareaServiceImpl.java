package com.riko.GSMarea.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riko.GSMarea.dao.GSMareaDao;
import com.riko.GSMarea.service.GSMareaService;
import com.riko.GSMarea.Conn;
import com.riko.GSMarea.DbConfig;

@Service
public class GSMareaServiceImpl extends DbConfig implements GSMareaService {
	
	@Autowired
	GSMareaDao dao;
	
	@Override
	public String checkConnection() throws Exception {
		
		String result = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = this.getConnection();
			ps = conn.prepareStatement("SELECT NOW()");
			rs = ps.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}finally {
			conn.close();
			ps.close();
			rs.close();
			this.getConnection().close();
		}
		return result;
	}
	
	@Override
	public List<Map<String, Object>> listProduct (String product_name, String brand_name) throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Conn conn = new Conn();
		try {
			conn.connection = getConnection();
			conn.connection.setAutoCommit(false);
			data = dao.listProduct(conn, product_name, brand_name);
			conn.connection.commit();
		} catch (Exception e) {
			if (conn != null) {
				conn.connection.rollback();
			}
			throw new Exception(e);
		} finally {
			conn.connection.close();
		}
		return data;
	}

	@Override
	public Map<String, Object> detailProduct(String product_id) throws Exception {
		Map<String, Object> data = new HashMap<>();
		Conn conn = new Conn();
		try {
			conn.connection = getConnection();
			conn.connection.setAutoCommit(false);
			data = dao.detailProduct(conn, product_id);
			conn.connection.commit();
		} catch (Exception e) {
			if (conn != null) {
				conn.connection.rollback();
			}
			throw new Exception(e);
		} finally {
			conn.connection.close();
		}
		return data;
	}

}
