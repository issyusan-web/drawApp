package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.User;
import service.MemberSelectService;

//ユーザーが登録したメンバーをホームに表示するためのコントローラー
@WebServlet("/memberSelectAllController")
public class MemberSelectAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ユーザーがログインに成功したら、ここに飛んでくる。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	//ユーザーがログインした段階で、ホーム画面に情報が表示されるように、
	//DBから、メンバー情報を全件取得する
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//セッションがあれば取得
		HttpSession session = request.getSession(false);
		if (session == null) {
			// ログインしてない場合
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}
			//セッションに保存されてるログインしたユーザー情報を取得
		User user = (User) session.getAttribute("user");
		//セッションにユーザー情報があるかチェック
		if (user == null) {
		    response.sendRedirect(request.getContextPath() + "/login");
		    return;
		}

		MemberSelectService memberSelectService = new MemberSelectService();
		//DBから全件取得するメソッド。取得したListを格納
		List<Member> memberList = memberSelectService.findAllMembers(user.getId());

		//セッションスコープに情報を保存し、ホーム画面にフォワードする。
		session.setAttribute("memberList", memberList);
		// ここで過去の抽選結果をクリア
		session.removeAttribute("finishedMemberList");
		session.removeAttribute("currentChoice");
		RequestDispatcher rd = request.getRequestDispatcher("/home");
		rd.forward(request, response);
	}

}
