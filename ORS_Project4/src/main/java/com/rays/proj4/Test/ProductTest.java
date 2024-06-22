package com.rays.proj4.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.rays.pro4.Bean.ProductBean;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.ProductModel;

public class ProductTest {
	public static void main(String[] args) throws DuplicateRecordException {
	testadd();

}

	private static void testadd()  throws DuplicateRecordException {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
			ProductBean bean = new ProductBean();
		bean.setId(1);
		bean.setProductName("Apple");
		bean.setProductAmount("1000");
		bean.setPurchaseDate(sdf.parse("01/01/2024"));
		bean.setProductCatagory("smartphone");
		
		ProductModel model = new ProductModel();
		long pk=model.add(bean);
		System.out.println("success");
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

}