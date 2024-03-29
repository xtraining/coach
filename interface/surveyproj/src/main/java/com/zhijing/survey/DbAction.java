package com.zhijing.survey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbAction {
	private Connection con;

	private void initConnection() {
		try {
			Class.forName(Config.getProperty("driverClassName")).newInstance();
			con = DriverManager.getConnection(
					Config.getProperty("url"), Config.getProperty("username"), Config.getProperty("password"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			con.close();
		} catch (Throwable t) {

		}
	}

	public void save(Survey survey) {
		PreparedStatement stmt = null;
		try {
			initConnection();
			String sql = "INSERT INTO survey(a1, a2, a3, a4, a5, a6, a7, ip, createTime, a, province, city, region) " +
					" VALUE (?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, ?, ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, survey.getA1());
			stmt.setString(2, survey.getA2());
			stmt.setString(3, survey.getA3());
			stmt.setString(4, survey.getA4());
			stmt.setString(5, survey.getA5());
			stmt.setString(6, survey.getA6());
			stmt.setString(7, survey.getA7());
			stmt.setString(8, survey.getIpAddress());
			stmt.setString(9, survey.getA());
			stmt.setString(10, survey.getProvince());
			stmt.setString(11, survey.getCity());
			stmt.setString(12, survey.getRegion());
			stmt.execute();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				closeConnection();
			} catch (Throwable e) {
				con = null;
			}
		}

	}
}
