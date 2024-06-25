package com.rays.pro4.Model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.RouteBean;
import com.rays.pro4.Util.JDBCDataSource;

public class RouteModel {

public Integer nextPK() throws Exception {

	int pk = 0;

	Connection conn = JDBCDataSource.getConnection();

	PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_route");

	ResultSet rs = pstmt.executeQuery();

	while (rs.next()) {
		pk = rs.getInt(1);
	}

	rs.close();

	return pk + 1;
}
public long add(RouteBean bean) throws Exception {

	int pk = 0;

	Connection conn = JDBCDataSource.getConnection();

	pk = nextPK();

	conn.setAutoCommit(false);

	PreparedStatement pstmt = conn.prepareStatement("insert into st_route values(?,?,?,?,?,?)");

	pstmt.setInt(1, pk);
	pstmt.setString(2, bean.getNumber());
	pstmt.setDate(3, new java.sql.Date(bean.getPurchasedate().getTime()));
	pstmt.setString(4, bean.getMobile());
	pstmt.setInt(5, bean.getInsuranceAmount());
	pstmt.setString(6, bean.getColour());
	
	

	int i = pstmt.executeUpdate();
	System.out.println("Route Add Successfully " + i);
	conn.commit();
	pstmt.close();

	return pk;
}
public void delete(RouteBean bean) throws Exception {

	Connection conn = JDBCDataSource.getConnection();

	conn.setAutoCommit(false);

	PreparedStatement pstmt = conn.prepareStatement("delete from st_route where id = ?");

	pstmt.setLong(1, bean.getId());

	int i = pstmt.executeUpdate();
	System.out.println("Route delete successfully " + i);
	conn.commit();

	pstmt.close();
}
public void update(RouteBean bean) throws Exception {

	Connection conn = JDBCDataSource.getConnection();

	conn.setAutoCommit(false); // Begin transaction

	PreparedStatement pstmt = conn
			.prepareStatement("update st_route set number = ?, purchase_date= ?, mobile = ?, insurance_amount = ?, colour = ? where id = ?");

	pstmt.setString(1, bean.getNumber());
	pstmt.setDate(2, new java.sql.Date(bean.getPurchasedate().getTime()));
	pstmt.setString(3, bean.getMobile());
	pstmt.setInt(4, bean.getInsuranceAmount());
	pstmt.setString(5, bean.getColour());
	pstmt.setLong(6, bean.getId());

	int i = pstmt.executeUpdate();

	System.out.println("route update successfully " + i);

	conn.commit();
	pstmt.close();

}
public RouteBean findByPK(long pk) throws Exception {

	String sql = "select * from st_route where id = ?";
	RouteBean bean = null;

	Connection conn = JDBCDataSource.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql);

	pstmt.setLong(1, pk);

	ResultSet rs = pstmt.executeQuery();

	while (rs.next()) {

		bean = new RouteBean();
		bean.setId(rs.getLong(1));
		bean.setNumber(rs.getString(2));
		bean.setPurchasedate(rs.getDate(3));
		bean.setMobile(rs.getString(4));
		bean.setInsuranceAmount(rs.getInt(5));
		bean.setColour(rs.getString(6));
		
	}

	rs.close();

	return bean;
}

public List search(RouteBean bean, int pageNo, int pageSize) throws Exception {

	StringBuffer sql = new StringBuffer("select * from st_route where 1=1");
	if (bean != null) {

		if (bean.getNumber() != null && bean.getNumber().length() > 0) {
			sql.append(" AND number like '" + bean.getNumber() + "%'");
		}
		if (bean.getPurchasedate() != null && bean.getPurchasedate().getTime() > 0) {
			Date d = new Date(bean.getPurchasedate().getTime());
			sql.append(" AND purchase_date = '" + d + "'");
			System.out.println("done");
		}
		if (bean.getMobile() != null && bean.getMobile().length() > 0) {
			// System.out.println(">>>>>>>>>>1111"+bean.getProductAmmount());
			sql.append(" AND Mobile like '" + bean.getMobile() + "%'");
		}
			if (bean.getInsuranceAmount()!= null && bean.getInsuranceAmount() > 0) {
				sql.append(" AND insurance_amount  " + bean.getInsuranceAmount() );
		}
		if (bean.getColour() != null && bean.getColour().length() > 0) {
			sql.append(" AND colour like '" + bean.getColour() + "%'");
		}

		if (bean.getId() > 0) {
			sql.append(" AND id = " + bean.getId());
		}

	}

	if (pageSize > 0) {

		pageNo = (pageNo - 1) * pageSize;

		sql.append(" Limit " + pageNo + ", " + pageSize);

	}

	System.out.println("sql query search >>= " + sql.toString());
	List list = new ArrayList();

	Connection conn = JDBCDataSource.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(sql.toString());

	ResultSet rs = pstmt.executeQuery();

	while (rs.next()) {

		bean = new RouteBean();
		bean.setId(rs.getLong(1));
		bean.setNumber(rs.getString(2));
		bean.setPurchasedate(rs.getDate(3));
		bean.setMobile(rs.getString(4));
		bean.setInsuranceAmount(rs.getInt(5));
		bean.setColour(rs.getString(6));
		list.add(bean);

	}
	rs.close();

	return list;

}
public List list() throws Exception {

	ArrayList list = new ArrayList();

	StringBuffer sql = new StringBuffer("select * from st_route");

	Connection conn = JDBCDataSource.getConnection();

	PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	ResultSet rs = pstmt.executeQuery();

	while (rs.next()) {

		RouteBean bean = new RouteBean();
		bean.setId(rs.getLong(1));
		bean.setNumber(rs.getString(2));
		bean.setPurchasedate(rs.getDate(3));
		bean.setMobile(rs.getString(4));
		bean.setInsuranceAmount(rs.getInt(5));
		bean.setColour(rs.getString(6)); 
		list.add(bean);

	}

	rs.close();

	return list;
}


}
