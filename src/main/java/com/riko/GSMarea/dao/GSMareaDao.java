package com.riko.GSMarea.dao;

import java.util.List;
import java.util.Map;
import com.riko.GSMarea.Conn;


public interface GSMareaDao {

	public List<Map<String, Object>> listProduct(Conn conn, String product_name, String brand_name) throws Exception;

	public Map<String, Object> detailProduct(Conn conn, String product_id) throws Exception;;
	
	
	
}
