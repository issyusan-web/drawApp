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
import service.UserEditService;

//管理者がユーザー情報を変更確定させるためのコントローラー。
@WebServlet("/UserEditConfirmController")
public class UserEditConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String userEditLoginId = request.getParameter("userEditLoginId");
		String userEditPassword = request.getParameter("userEditPassword");
		String userEditName = request.getParameter("userEditName");
		String idStr = request.getParameter("editId");
		if (idStr == null || !idStr.matches("\\d+")) {
			request.setAttribute("editError", "不正なリクエストです。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userEdit.jsp");
			rd.forward(request, response);
			return;
		}
		int userEditId = Integer.parseInt(idStr);

		// 更新後の会員情報をDomainに格納
		User user = new User(userEditLoginId, userEditPassword, userEditName);
		user.setId(userEditId);

		UserEditService editService = new UserEditService();
		boolean result = editService.userEditDo(user);

		// 更新に成功した場合はresultにtrueが格納されている
		if (result) {
			request.setAttribute("targetName", userEditName);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userEditDone.jsp");
			rd.forward(request, response);
		} else {
			// 会員情報の更新に失敗した場合はエラーメッセージを用意して編集画面へ戻す
			request.setAttribute("editError", "メンバー情報の更新に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userEdit.jsp");
			rd.forward(request, response);
		}
	}

}
