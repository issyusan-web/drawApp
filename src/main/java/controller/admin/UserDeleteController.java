package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.SelectUserDeleteService;
import service.UserDeleteService;

//管理者がユーザー情報を削除するためのコントローラー。
@WebServlet("/UserDeleteController")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ユーザー情報一覧からここにくる。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		//任意のユーザーのIDを取得
		String userId = request.getParameter("userId");
		if (userId == null || !userId.matches("\\d+")) {
			request.setAttribute("errorMsg", "不正なリクエストです。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userAll.jsp");
			rd.forward(request, response);
			return;
		}

		// DBから対象ユーザー情報を取得
		SelectUserDeleteService sud = new SelectUserDeleteService();
		User target = sud.selectUserDelete(Integer.parseInt(userId));

		if (target == null) {
			request.setAttribute("errorMsg", "ユーザー情報が取得できませんでした。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userAll.jsp");
			rd.forward(request, response);
			return;
		}

		request.setAttribute("target", target);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userDeleteConfirm.jsp");
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
		String userIdStr = request.getParameter("userId");
		if (userIdStr == null || !userIdStr.matches("\\d+")) {
			request.setAttribute("errorMsg", "不正なリクエストです。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userAll.jsp");
			rd.forward(request, response);
			return;
		}
		int userId = Integer.parseInt(userIdStr);

		UserDeleteService uds = new UserDeleteService();
		boolean result = uds.userDeleteDo(userId);

		// 削除が成功した場合はresultにtrueが格納されている
		if (result) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userDeleteDone.jsp");
			rd.forward(request, response);
		} else {
			// 削除に失敗した場合、エラーメッセージを用意して削除確認画面へ戻す
			request.setAttribute("deleteError", "登録情報の削除に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userAll.jsp");
			rd.forward(request, response);
		}
	}
}
