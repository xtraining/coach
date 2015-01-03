package com.zhiqin.domain;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	public void save(DomainHistory history) {
		PreparedStatement stmt = null;
		try {
			initConnection();
			String sql = "INSERT INTO domain_history(ip, createTime, tunnel) " +
					" VALUE (?, now(), ?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, history.getIpAddress());
			stmt.setString(2, history.getTunnel());
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

	public String getForwardUrlByTunnel(String tunnel) {
		PreparedStatement stmt = null;
		String forwardUrl = null;
		try {
			initConnection();
			String sql = "select forwardUrl from domain_config where tunnel = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tunnel);
			ResultSet rs = stmt.executeQuery();
			if(rs.first()){
				forwardUrl = rs.getString(1);
			}
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
		return forwardUrl;
	}
}
