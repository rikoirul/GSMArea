package com.riko.GSMarea.constant;

public class ConstandSQL {
	
	public static final String getListProduct1= "select b.brand_desc, p.product_id, p.product_name, p.release_date "
			+ "from public.product p "
			+ "left join public.brand b "
			+ "on b.brand_id = p.brand_id "
			+ "where "
			+ "LOWER(p.product_name) like ? or LOWER(b.brand_desc) like ? "
			+ "and p.is_delete  = false ";
	
	public static final String getListProduct2= "select b.brand_desc, p.product_id, p.product_name, p.release_date "
			+ "from public.product p "
			+ "left join public.brand b "
			+ "on b.brand_id = p.brand_id "
			+ "where "
			+ "LOWER(b.brand_desc) like ? "
			+ "and p.is_delete  = false ";
	
	public static final String getListProduct3= "select b.brand_desc, p.product_id, p.product_name, p.release_date "
			+ "from public.product p "
			+ "left join public.brand b "
			+ "on b.brand_id = p.brand_id "
			+ "where "
			+ "LOWER(p.product_name) like ? "
			+ "and p.is_delete  = false ";

	public static String getdetailProduct = "select * from public.product_detail where product_id = ?";
}
