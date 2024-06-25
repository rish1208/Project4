package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.VehicleTrackingBean;
import com.rays.pro4.Util.JDBCDataSource;

public class VehicleTrackingModel {
	public Integer nextPK() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_vehicle");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}
	public long add(VehicleTrackingBean bean) throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_vehicle values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
	
		pstmt.setDouble(2, bean.getLat());
		pstmt.setDouble(3, bean.getLongId());
		pstmt.setString(4, bean.getVehicleId());
		pstmt.setDate(5, new java.sql.Date(bean.getDate().getTime()));

		int i = pstmt.executeUpdate();
		System.out.println("Vehicle Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}
	public void delete(VehicleTrackingBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from st_vehicle where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Vehicle delete successfully " + i);
		conn.commit();

		pstmt.close();
	}
	public void update(VehicleTrackingBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn
				.prepareStatement("update st_vehicle set lat = ?, longid= ?,vehicleid = ?, date = ? where id = ?");
		pstmt.setDouble(1, bean.getLat());
		pstmt.setDouble(2, bean.getLongId());
		pstmt.setString(3, bean.getVehicleId());
		pstmt.setDate(4, new java.sql.Date(bean.getDate().getTime()));


		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Vehicle update successfully " + i);

		conn.commit();
		pstmt.close();

	}
	public VehicleTrackingBean findByPK(long pk) throws Exception {

		String sql = "select * from st_vehicle where id = ?";
		VehicleTrackingBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new VehicleTrackingBean();
			bean.setId(rs.getLong(1));
			
			bean.setLat(rs.getDouble(2));
			bean.setLongId(rs.getDouble(3));
			bean.setVehicleId(rs.getString(4));
			bean.setDate(rs.getDate(5));
		}

		rs.close();

		return bean;
	}

	public List search(VehicleTrackingBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_vehicle where 1=1");
		if (bean != null) {

			
			if (bean.getDate() != null && bean.getDate().getTime() > 0) {
				Date d = new Date(bean.getDate().getTime());
				sql.append(" AND date = '" + d + "'");
				System.out.println("done");
			}
			if  (bean.getLat() != null && bean.getLat() > 0) {
				sql.append(" AND lat=" + bean.getLat() );
			}

			if  (bean.getLongId() != null && bean.getLongId() > 0) {
				sql.append(" AND longid=" + bean.getLongId() );
			}

			if (bean.getVehicleId() != null && bean.getVehicleId().length() > 0) {
				sql.append(" AND vehicleid like '" + bean.getVehicleId() + "%'");
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

			bean = new VehicleTrackingBean();
             bean.setId(rs.getLong(1));
			
			bean.setLat(rs.getDouble(2));
			bean.setLongId(rs.getDouble(3));
			bean.setVehicleId(rs.getString(4));
			bean.setDate(rs.getDate(5));

			list.add(bean);

		}
		rs.close();

		return list;

	}
	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_vehicle");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			VehicleTrackingBean bean = new VehicleTrackingBean();
			bean.setId(rs.getLong(1));
			bean.setLat(rs.getDouble(2));
			bean.setLongId(rs.getDouble(3));
			bean.setVehicleId(rs.getString(4));
			bean.setDate(rs.getDate(5));
			list.add(bean);

		}

		rs.close();

		return list;
	}


	}



