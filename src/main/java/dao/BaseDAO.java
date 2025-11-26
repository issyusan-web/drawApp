package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	//DBへの接続準備
	//ローカル PostgreSQL 接続設定
	private static final String DB_URL = "jdbc:postgresql://localhost:5434/drawapp";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "postgres";

	// EC2 PostgreSQL 接続設定
//	private static final String DB_URL = "jdbc:postgresql://localhost:5432/drawapp";
//	private static final String DB_USER = "admin";
//	private static final String DB_PASSWORD = "password";

	// コンストラクタでドライバ登録（必要な場合）
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//DBへの接続
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}
}