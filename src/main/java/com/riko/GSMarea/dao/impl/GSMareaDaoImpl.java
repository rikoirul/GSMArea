package com.riko.GSMarea.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.riko.GSMarea.constant.ConstandSQL;
import com.riko.GSMarea.dao.GSMareaDao;
import com.riko.GSMarea.Conn;

@Repository
public class GSMareaDaoImpl implements GSMareaDao {

	@Override
	public List<Map<String, Object>> listProduct(Conn conn, String product_name, String brand_name) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			if (product_name == ""){
				brand_name = "%" + brand_name + "%";
				conn.pr = conn.connection.prepareStatement(ConstandSQL.getListProduct2);
				conn.pr.setString(1, brand_name);
				System.out.println("brand");
			} else if (brand_name == ""){
				product_name = "%" + product_name + "%";
				conn.pr = conn.connection.prepareStatement(ConstandSQL.getListProduct3);
				conn.pr.setString(1, product_name);
				System.out.println("product");
			} else {
				product_name = "%" + product_name + "%";
				brand_name = "%" + brand_name + "%";
				conn.pr = conn.connection.prepareStatement(ConstandSQL.getListProduct1);
				conn.pr.setString(1, product_name);
				conn.pr.setString(2, brand_name);
				System.out.println("kumplit");
			}
			
			conn.rs = conn.pr.executeQuery();
			while (conn.rs.next()) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("brand", conn.rs.getString(1));
				data.put("product_id", conn.rs.getString(2));
				data.put("product", conn.rs.getString(3));
				data.put("release_date", conn.rs.getString(4));
				result.add(data);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			conn.closeStatement();
		}
		return result;
	}

	@Override
	public Map<String, Object> detailProduct(Conn conn, String product_id) throws Exception {
		Map<String, Object> result = new HashMap<>();
		try {

			conn.pr = conn.connection.prepareStatement(ConstandSQL.getdetailProduct);
			conn.pr.setString(1, product_id);
			conn.rs = conn.pr.executeQuery();
			while (conn.rs.next()) {
				result.put("product_id", conn.rs.getString(1));
				result.put("product_name", conn.rs.getString(2));
				result.put("network", conn.rs.getString(3));
				result.put("dimensions", conn.rs.getString(4));
				result.put("display_type", conn.rs.getString(5));
				result.put("display_size", conn.rs.getString(6));
				result.put("display_resolution", conn.rs.getString(7));
				result.put("operating_system", conn.rs.getString(8));
				result.put("chipset", conn.rs.getString(9));
				result.put("storage", conn.rs.getString(10));
				result.put("camera_res_main", conn.rs.getString(11));
				result.put("camera_res_sec", conn.rs.getString(12));
				result.put("battery", conn.rs.getString(13));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			conn.closeStatement();
		}
		return result;
	}
	
	

}
