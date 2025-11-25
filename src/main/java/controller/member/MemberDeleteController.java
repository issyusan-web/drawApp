package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.MemberDeleteService;

//登録されてる全メンバーを削除するためのコントローラー。
@WebServlet("/memberDelete")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		//削除確認画面にフォワードする。
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberDeleteConfirm.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		//ユーザーIDの取得
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		MemberDeleteService memberDeleteService = new MemberDeleteService();
		int count = memberDeleteService.deleteAllMembers(userId);

		//スコープ内を削除
		session.removeAttribute("memberList");
		session.removeAttribute("finishedMemberList");
		session.removeAttribute("currentChoice");
		//何件成功したか表示
		if (count > 0) {
			request.setAttribute("msg", count + "件削除成功");
		} else {
			request.setAttribute("msg", "全件削除失敗");
		}

		//削除成功件数に応じて返ってきたメッセージをリクエストスコープに保存し、削除完了ページにフォワードする。
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberDeleteDone.jsp");
		rd.forward(request, response);
	}
}