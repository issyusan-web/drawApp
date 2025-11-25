package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import service.MemberService;

//ユーザーが登録したメンバーをランダムに抽選するためのコントローラー
@WebServlet("/randomController")
public class RandomController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションの取得
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.setAttribute("choiceError", "メンバー情報が存在しません。");
			forwardHome(request, response);
			return;
		}

		//前回の当選者を「終了したメンバー側」に回す
		//終了した人をスコープから取得

		//終了したメンバーを保存するListを取得
		@SuppressWarnings("unchecked")
		List<Member> finishedMemberList = (List<Member>) session.getAttribute("finishedMemberList");
		//まだListが無ければ作成
		if (finishedMemberList == null) {
			finishedMemberList = new ArrayList<>();
		}

		//前回の currentChoice を終了メンバーに移動
		Member previousChoice = (Member) session.getAttribute("currentChoice");
		//終了した人がいれば、Listに追加
		if (previousChoice != null) {
			finishedMemberList.add(previousChoice);
		}
		// 更新してセッションに戻す
		session.setAttribute("finishedMemberList", finishedMemberList);

		//1-1メンバー情報が入ったListをスコープから取得
		//このアノテーションは「開発者が型安全性を理解したうえでキャストしている」という宣言
		@SuppressWarnings("unchecked")
		List<Member> memberList = (List<Member>) session.getAttribute("memberList");
		if (memberList == null || memberList.isEmpty()) {
			request.setAttribute("choiceError", "全てのメンバーを選び終わりました。");
			forwardHome(request, response);
			return;
		}

		//新しい当選者をランダムに選ぶ
		MemberService memberService = new MemberService();
		//1-2削除された要素が返ってくる。
		Member choiceMember = memberService.randomChoice(memberList);

		//中身が入ってるなら、、
		if (choiceMember == null) {
			request.setAttribute("choiceError", "全てのメンバーを選び終わりました。");
			forwardHome(request, response);
			return;
		}
		//1-3更新されたリストをセッションスコープに保存
		session.setAttribute("memberList", memberList);
		//1-4削除されたメンバーをリクエストスコープに保存
		request.setAttribute("choiceMember", choiceMember);
		//1-5次回の抽選時に、終了者として扱うためにセッションに保存
		session.setAttribute("currentChoice", choiceMember);
		forwardHome(request, response);
	}

	private void forwardHome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		rd.forward(request, response);
	}
}
