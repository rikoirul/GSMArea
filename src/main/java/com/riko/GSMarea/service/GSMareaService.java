package com.riko.GSMarea.service;

import java.util.List;
import java.util.Map;

public interface GSMareaService {

	public String checkConnection() throws Exception;
	public List<Map<String, Object>> listProduct(String product_name, String brand_name) throws Exception ;
	public Map<String, Object> detailProduct(String product_id) throws Exception ;;
	
}
