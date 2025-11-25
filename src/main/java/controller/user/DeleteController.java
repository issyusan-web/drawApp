package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserDeleteService;

//ユーザーを削除するためのコントローラー。
@WebServlet("/delete")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// deleteConfirm.jspへフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/deleteConfirm.jsp");
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
		int id = user.getId();
		
		// 削除したユーザー情報のloginIdをDomainに格納
		UserDeleteService deleteService = new UserDeleteService();
		boolean result = deleteService.userDeleteDo(id);

		// 削除が成功した場合はresultにtrueが格納されている
		if (result) {
			// セッションの破棄
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/deleteDone.jsp");
			rd.forward(request, response);
		} else {
			// 削除に失敗した場合、エラーメッセージを用意して削除確認画面へ戻す
			request.setAttribute("deleteError", "登録情報の削除に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/deleteConfirm.jsp");
			rd.forward(request, response);
		}
	}

}
