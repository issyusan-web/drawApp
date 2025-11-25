package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MemberDTO;

public class MemberDAO extends BaseDAO {

	//DBから全件取得し、各インスタンスが格納されたListを返すメソッド。
	public List<MemberDTO> selectAll(int userId) {
		//取得したデータを持ったインスタンスたちを格納するList
		List<MemberDTO> list = new ArrayList<>();
		//SQLの準備
		String sql = "SELECT id,name,users_id FROM member WHERE users_id = ? ORDER BY id";

		//DBに接続
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);

			//SQLの実行
			try (ResultSet rs = ps.executeQuery()) {
				//取得した情報でDTOを作成し、Listに追加
				while (rs.next()) {
					MemberDTO member = new MemberDTO(rs.getString("name"), rs.getInt("users_id"));
					member.setId(rs.getInt("id"));
					list.add(member);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//受け取ったIDを元に、１件分の情報を取得し、DTOインスタンスを返すメソッド。
	public MemberDTO selectById(int memid) {
		MemberDTO mdto = null;
		//SQLの準備
		String sql = "SELECT id,name,users_id FROM member WHERE id = ?";

		//DBに接続
		try (Connection conn = getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {

			//受け取ったIDをSQLにセット
			ps.setInt(1, memid);
			//SQLの実行
			try (ResultSet rs = ps.executeQuery()) {
				//取得した情報をDTOに保存
				if (rs.next()) {
					mdto = new MemberDTO(rs.getString("name"), rs.getInt("users_id"));
					mdto.setId(rs.getInt("id"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mdto;
	}

	//複数の入力情報を全てINSERTするメソッド
	public int insert(List<MemberDTO> mdtos) {
		int result = 0;
		//SQLの準備
		String sql = "INSERT INTO member(name,users_id) values(?,?)";

		//DB接続、トランザクション開始
		try (Connection conn = getConnection();
				TransactionManager tm = new TransactionManager(conn);
				PreparedStatement ps = conn.prepareStatement(sql);) {

			//受け取ったListから１件ずつDTOインスタンスを取り出し、順番にINSERT文を実行するメソッド
			for (MemberDTO mdto : mdtos) {
				//DTOからＩＤと名前を取り出し、SQLにセット
				ps.setString(1, mdto.getName());
				ps.setInt(2, mdto.getUserId());
				//追加成功件数の合計を出す
				result += ps.executeUpdate();
			}
			//コミットする
			tm.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	//DBの情報を全て削除するメソッド
	public int deleteAll(int userId) {
		int result = 0;
		//SQLの準備
		String sql = "DELETE FROM member WHERE users_id = ?";

		//DBに接続、トランザクション開始
		try (Connection conn = getConnection();
				TransactionManager tm = new TransactionManager(conn);
				PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);
			//SQLの実行
			result = ps.executeUpdate();
			//コミットする
			tm.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 受け取ったIDを元に1件分削除するメソッド。
	public int deleteById(int memId) {
		int result = 0;
		//SQLの準備
		String sql = "DELETE FROM member WHERE id = ?";

		//DBに接続、トランザクション開始
		try (Connection conn = getConnection();
				TransactionManager tm = new TransactionManager(conn);
				PreparedStatement ps = conn.prepareStatement(sql);) {

			//受け取ったDTOからIDを取得して、SQLにセット
			ps.setInt(1, memId);
			//SQLの実行
			result = ps.executeUpdate();
			//コミットする
			tm.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
