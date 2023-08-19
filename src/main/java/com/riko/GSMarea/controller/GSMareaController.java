package com.riko.GSMarea.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.riko.GSMarea.service.GSMareaService;

@RestController
public class GSMareaController {
	
	@Autowired
	GSMareaService service;
	
	public static final Logger logger = LoggerFactory.getLogger(GSMareaController.class);
	
	@GetMapping("/")
	public String index() throws Exception {
		String result = "";
		try {
			result = "Koneksi Berhasil " + service.checkConnection();
		}catch (Exception e) {
			result = "Koneksi Gagal !!<br>" + e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value = "/listProduct", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listProduct(HttpServletRequest request,
			@RequestBody Map<String, Object> body) {
		
		String product_name =body.get("product_name").toString().toLowerCase().trim();		
		String brand_name =body.get("brand_name").toString().toLowerCase().trim();
		
		List<Map<String, Object>> result = new ArrayList<>();
		Map<String, Object> output = new HashMap<>();
		
		try {
			result = service.listProduct(product_name, brand_name);
			
			if (result == null || result.isEmpty()) {
				output.put("data", null);
				output.put("message", "Data tidak ditemuakn");
				output.put("status", false);
				return new ResponseEntity<Map<String, Object>>(output, HttpStatus.NOT_FOUND);
			} else {
				output.put("data", result);
				output.put("message", "");
				output.put("status", true);
				return new ResponseEntity<Map<String, Object>>(output, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			output.put("data", null);
			output.put("message", e.getMessage());
			output.put("status", false);
			return new ResponseEntity<Map<String, Object>>(output, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/detailProduct", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> detailProduct(HttpServletRequest request,
			@RequestBody Map<String, Object> body) {
		
		String product_id =body.get("product_id").toString().toLowerCase().trim();		
		
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> output = new HashMap<>();
		
		try {
			result = service.detailProduct(product_id);
			
			if (result == null	|| result.isEmpty()) {
				output.put("data", null);
				output.put("message", "Data tidak ditemuakn");
				output.put("status", false);
				return new ResponseEntity<Map<String, Object>>(output, HttpStatus.NOT_FOUND);
			} else {
				output.put("data", result);
				output.put("message", "");
				output.put("status", true);
				return new ResponseEntity<Map<String, Object>>(output, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
			output.put("data", null);
			output.put("message", e.getMessage());
			output.put("status", false);
			return new ResponseEntity<Map<String, Object>>(output, HttpStatus.BAD_REQUEST);
		}
	}

}
