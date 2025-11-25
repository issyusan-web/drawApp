package service;

import java.util.ArrayList;
import java.util.List;

import dao.MemberDAO;
import domain.Member;
import dto.MemberDTO;

//メンバー情報の取得に関するserviceクラス
public class MemberSelectService {

	//受け取ったIDを元に、DBから１件分の情報を取得するselectByIdメソッドを呼び出す。
	public Member findMemberById(int memId) {
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.selectById(memId);

		//情報が取得出来ていればdomainを作成し返す。
		if (mdto != null) {
			Member member = new Member(mdto.getId(),mdto.getName(), mdto.getUserId());
			return member;
		} else {
			return null;
		}
	}

	//DBからメンバー全件の情報を取得するselectAllメソッドを呼び出す。
	public List<Member> findAllMembers(int userId) {
		MemberDAO dao = new MemberDAO();
		List<MemberDTO> dtoList = dao.selectAll(userId);
		List<Member> members = new ArrayList<>();

		//取得した全件分のインスタンスを保存するListを準備
		for (MemberDTO dto : dtoList) {
			members.add(new Member(dto.getId(),dto.getName(), dto.getUserId()));
		}
		return members;
	}
}
