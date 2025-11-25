package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import validation.Validation;

//ユーザー情報を変更するコントローラー。
@WebServlet("/edit")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// edit.jsp へ遷移する。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp");
		rd.forward(request, response);
	}

	//edit.jspから情報を受け取って情報を変更する。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// ユーザーが入力した値を取得 
		String editLoginId = request.getParameter("editLoginId");
		String editPassword = request.getParameter("editPassword");
		String editName = request.getParameter("editName");

		// バリデーション 
		Validation validation = new Validation();
		validation.isBlank("ログインID", editLoginId);
		validation.isBlank("パスワード", editPassword);
		validation.isBlank("お名前", editName);
		validation.length("ログインID", editLoginId, 1, 8);
		validation.length("パスワード", editPassword, 2, 10);
		validation.length("お名前", editName, 1, 10);

		// 入力値エラーがあった場合、edit.jspに戻る。
		if (validation.hasErrorMsg()) {
			request.setAttribute("errorMsg",
					validation.getErrorMsgList());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp");
			rd.forward(request, response);
			return;
		} else {
			// 変更後の値をリクエストスコープへ保存して確認画面へ 
			request.setAttribute("editLoginId", editLoginId);
			request.setAttribute("editName", editName);
			request.setAttribute("editPassword", editPassword);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/editConfirm.jsp");
			rd.forward(request, response);
		}
	}

}
