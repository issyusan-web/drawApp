package service;

import dao.MemberDAO;

//メンバー情報の削除に関するserviceクラス
public class MemberDeleteService {

	//DBのメンバー情報をIDを元に削除するメソッド
	public int deleteMember(int memId) {
		//DAOにIDを渡し、該当データを削除する。
		MemberDAO mdao = new MemberDAO();
		int result = mdao.deleteById(memId);

		 //削除件数を返す（1件削除できた場合は1が返る）
		return result;
	}

	//DBのメンバー情報をすべて削除するメソッド
	public int deleteAllMembers(int userId) {
		MemberDAO mdao = new MemberDAO();
		//DAOのdeleteAllメソッドを呼び出して、成功件数を返す。
		int result = mdao.deleteAll(userId);
		return result;
	}
}
