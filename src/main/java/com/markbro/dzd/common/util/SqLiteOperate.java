package com.markbro.dzd.common.util;

import java.sql.*;

/**
 * SqlLite配置管理
 * @author yangsl
 *
 */
public class SqLiteOperate {
	
	private String path = "";
	private final int one = 1;
	private final int two = 2;
	private final int three = 3;
	private final int four = 4;

	/**
	 * 构造器
	 * @param path
	 */
	public SqLiteOperate(String path) {
		super();
		this.path = "jdbc:sqlite:" + path + "WEB-INF/classes/sales.db";
	}

	/**
	 * 插入
	 * 
	 * 创建时间  2014-3-25
	 * 创建人 yangsl
	 * @param simID
	 * @param zzid
	 * @param lx
	 * @throws Exception
	 *
	 */
	public void insert(String simID, String zzid, String lx, String zdlx) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(path);
		String sql = "insert into t_ydxs_mobile_sim(id,simId,zzid,lx,zdlx) values (?,?,?,?,?);";
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.setString(two, Md5.getMd5(simID));
		prep.setString(three, zzid);
		prep.setString(four, lx);
		prep.setString(5, zdlx);
		prep.addBatch();
		conn.setAutoCommit(false);
		prep.executeBatch();
		conn.setAutoCommit(true);
		conn.close();
	}

	/**
	 * 根据sim卡串号和zzid删除数据
	 * 
	 * 创建时间  2014-3-25
	 * 创建人 yangsl
	 * @param simID
	 * @param zzid
	 * @throws Exception
	 *
	 */
	public void delete(String simID, String zzid) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(path);
		PreparedStatement prep = conn.prepareStatement("delete from t_ydxs_mobile_sim where simId=? and zzid=?;");
		prep.setString(one, Md5.getMd5(simID));
		prep.setString(two, zzid);
		prep.addBatch();
		conn.setAutoCommit(false);
		prep.executeBatch();
		conn.setAutoCommit(true);
		conn.close();
	}

	/**
	 * 验证SIMID
	 * 
	 * 创建时间  2014-3-25
	 * 创建人 yangsl
	 * @param simID
	 * @return
	 * @throws Exception
	 *
	 */
	public boolean query(String simID) throws Exception {
		Class.forName("org.sqlite.JDBC");
		boolean flag = false;
		Connection conn = DriverManager.getConnection(path);
		Statement stat = conn.createStatement();
		String sql = "select * from t_ydxs_mobile_sim where simId='" + Md5.getMd5(simID) + "'";
		ResultSet rs = stat.executeQuery(sql);
		if (rs.next()) {
			flag = true;
		}
		rs.close();
		conn.close();
		return flag;
	}

	/**
	 * 获取zzid
	 * 
	 * 创建时间  2014-3-25
	 * 创建人 yangsl
	 * @param simID
	 * @return
	 * @throws Exception
	 *
	 */
	public String getZzidBySimID(String simID) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(path);
		Statement stat = conn.createStatement();
		String sql = "select zzid from t_ydxs_mobile_sim where simId='" + Md5.getMd5(simID) + "'";
		String zzid = "";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next()) {
			zzid = rs.getString("zzid");
		}
		rs.close();
		conn.close();
		return zzid;
	}

	/**
	 * 获取用户类型（区分用户还是客户会员）
	 * 
	 * 创建时间  2014-3-25 上午9:35:57 
	 * 创建人 yangsl
	 * @param simID
	 * @return
	 * @throws Exception
	 *
	 */
	public String getLxBySimID(String simID) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(path);
		Statement stat = conn.createStatement();
		String sql = "select lx from t_ydxs_mobile_sim where simId='" + Md5.getMd5(simID) + "'";
		String yhlx = "";
		ResultSet rs = stat.executeQuery(sql);
		while (rs.next()) {
			yhlx = rs.getString("lx");
		}
		rs.close();
		conn.close();
		return yhlx;
	}
}
