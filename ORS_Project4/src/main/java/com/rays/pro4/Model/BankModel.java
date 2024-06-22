package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.BankBean;
import com.rays.pro4.Util.JDBCDataSource;

public class BankModel {
	public Integer nextPk() throws Exception {

		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_bank");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getInt(1);
		}
		rs.close();
		return pk + 1;

	}

	public long add(BankBean bean) throws Exception {

		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		pk = nextPk();
		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into st_bank values(?,?,?,?,?)");
		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getBankName());
		pstmt.setString(3, bean.getAccountNo());
		pstmt.setString(4, bean.getAmount());
		pstmt.setString(5, bean.getAccountHolder());

		int i = pstmt.executeUpdate();
		System.out.println("product add successfully" + i);
		conn.commit();
		pstmt.close();
		return pk;

	}

	public void delete(BankBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement("delete from st_bank where id=?");
		pstmt.setLong(1, bean.getId());
		int i = pstmt.executeUpdate();
		System.out.println("Product delete successfully " + i);
		conn.commit();

		pstmt.close();

	}

	public void update(BankBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_bank set ; = ?, account_No = ?, amount = ?, account_Holder = ? where id = ?");

		pstmt.setString(1, bean.getBankName());
		pstmt.setString(2, bean.getAccountNo());
		pstmt.setString(3, bean.getAmount());
		pstmt.setString(4, bean.getAccountHolder());
		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("product update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public BankBean findByPK(long pk) throws Exception {

		String sql = "select * from st_bank where id = ?";
		BankBean bean = null;

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new BankBean();
			bean.setId(rs.getLong(1));
			bean.setBankName(rs.getString(2));
			bean.setAccountNo(rs.getString(3));
			bean.setAmount(rs.getString(4));
			bean.setAccountHolder(rs.getString(5));

		}

		rs.close();

		return bean;
	}

	public List search(BankBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_bank where 1=1");
		if (bean != null) {

			if (bean.getBankName() != null && bean.getBankName().length() > 0) {
				sql.append(" AND bank_Name like '" + bean.getBankName() + "%'");
			}

			if (bean.getAccountNo() != null && bean.getAccountNo().length() > 0) {
				sql.append(" AND account_No like '" + bean.getAccountNo() + "%'");
			}

			if (bean.getAmount() != null && bean.getAmount().length() > 0) {
				sql.append(" AND amount like '" + bean.getAmount() + "%'");
			}

			if (bean.getAccountHolder() != null && bean.getAccountHolder().length() > 0) {
				sql.append(" AND account_Holder like '" + bean.getAccountHolder() + "%'");
				System.out.println("done");
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

			System.out.println("list display");

			bean = new BankBean();
			bean.setId(rs.getLong(1));
			
			bean.setBankName(rs.getString(2));
			bean.setAccountNo(rs.getString(3));
			bean.setAmount(rs.getString(4));
			bean.setAccountHolder(rs.getString(5));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_bank");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			BankBean bean = new BankBean();

			bean.setId(rs.getLong(1));
			bean.setBankName(rs.getString(2));
			bean.setAccountNo(rs.getString(3));
			bean.setAmount(rs.getString(4));
			bean.setAccountHolder(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

}


