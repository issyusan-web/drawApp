package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;

//usersテーブルに対するDB操作を行うDAO
public class UserDAO extends BaseDAO {

	//受け取ったloginIdを元にDBからユーザー情報を取得するメソッド。
	public UserDTO selectByLoginId(String loginId) {
		UserDTO dto = null;
		String sql = "SELECT id,login_id,password,name,auth FROM users WHERE login_id = ?";

		//DBに接続、SQL文の準備
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			// 引数で受け取った変数 loginId で検索
			ps.setString(1, loginId);

			////SQLの実行
			try (ResultSet rs = ps.executeQuery()) {

				// 検索にヒットしたデータがあれば DTO に格納
				if (rs.next()) {
					dto = new UserDTO();
					dto.setId(rs.getInt("id"));
					dto.setLoginId(rs.getString("login_id"));
					dto.setPassword(rs.getString("password"));
					dto.setName(rs.getString("name"));
					dto.setAuth(rs.getInt("auth"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public int insert(UserDTO dto) {
		int result = 0;
		String sql = "INSERT INTO users(login_id, password, name) VALUES(?, ?, ?)";

		// DBへ接続、トランザクション開始
		try (Connection conn = getConnection();
				TransactionManager tm = new TransactionManager(conn);
				PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, dto.getLoginId());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getName());

			// DB への insert が成功した件数が int 型で返却される 
			result = ps.executeUpdate();
			tm.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int edit(UserDTO dto) {
		int result = 0;
		String sql = "UPDATE users SET password = ?, name = ?,login_id = ? WHERE id = ?";

		// DBへ接続、トランザクション開始
		try (Connection conn = getConnection();
				TransactionManager tm = new TransactionManager(conn);
				PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setString(1, dto.getPassword());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getLoginId());
			ps.setInt(4, dto.getId());

			// DB への update が成功した件数が int 型で返却される 
			result = ps.executeUpdate();
			tm.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(int id) {
		int result = 0;
		String sql = "DELETE FROM users WHERE id = ?";

		// DBへ接続、トランザクション開始
		try (Connection conn = getConnection();
				TransactionManager tm = new TransactionManager(conn);
				PreparedStatement ps = conn.prepareStatement(sql);) {

			ps.setInt(1, id);

			// DBへのdeleteが成功した件数がint型で返却される
			result = ps.executeUpdate();
			tm.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<UserDTO> selectAll() {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		UserDTO dto = null;
		String sql = "SELECT id,login_id,password,name,auth FROM users ORDER BY id";

		// データベースへ接続
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getInt("id"));
				dto.setLoginId(rs.getString("login_id"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
				dto.setAuth(rs.getInt("auth"));
				userList.add(dto);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	//IDを元に１人分のユーザー情報を取得する
	public UserDTO selectById(int id) {
		UserDTO dto = null;
		String sql = "SELECT id,login_id,password,name,auth FROM users WHERE id = ?";
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					dto = new UserDTO();
					dto.setId(rs.getInt("id"));
					dto.setLoginId(rs.getString("login_id"));
					dto.setPassword(rs.getString("password"));
					dto.setName(rs.getString("name"));
					dto.setAuth(rs.getInt("auth"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}