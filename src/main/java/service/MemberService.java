package service;

import java.util.ArrayList;
import java.util.List;

import domain.Member;

//メンバー情報を格納するListに対する操作を行うサービス。
public class MemberService {

	//削除されたインスタンスを含めず、新しいListを作成するメソッド。
	public List<Member> removeMemberFromList(List<Member> members, Member target) {
		List<Member> newMembers = new ArrayList<Member>();
		for (Member m : members) {
			//受け取ったListのIDと、削除対象のIDが一致しなければ、Listに追加する。
			if (m.getId() != target.getId()) {
				newMembers.add(m);
			}
		}
		return newMembers;
	}

	//生成されたランダムな数字に対応するListの要素を返すメソッド。
	public Member randomChoice(List<Member> members) {
		if (members == null || members.isEmpty()) {
			return null; // 空リストなら何もしない
		}
		//Listの要素の数、ランダムな整数を生成
		int random = (int) (Math.random() * members.size());
		//Listから要素を削除し、その削除された要素を返す。
		return members.remove(random);
	}
}
