package service;

import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import domain.Member;
import dto.MemberDTO;

//メンバー情報の追加に関するserviceクラス
public class MemberRegisterService {

	//DBに情報を追加するinsertメソッドを呼び出す。
	public int registerMembers(List<Member> members) {
		List<MemberDTO> list = new ArrayList<>();
		for (Member mem : members) {
			//受け取ったdomainからDTOを作成し、Listに追加。
			MemberDTO mdto = new MemberDTO(mem.getName(), mem.getUserId());
			list.add(mdto);
		}

		MemberDAO mDAO = new MemberDAO();
		//DAOのinsertメソッドを呼び出して、成功件数を返す。
		int result = mDAO.insert(list);
		return result;
	}

}
