package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;
import service.UserSelectService;
import validation.Validation;

//管理者がユーザー情報を変更するためのコントローラー。
@WebServlet("/UserEditController")
public class UserEditController extends HttpServlet {
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
			response.sendRedirect(request.getContextPath() + "/userList");
			return;
		}

		// DBから対象ユーザー情報を取得
		UserSelectService service = new UserSelectService();
		UserDTO target = service.findById(Integer.parseInt(userId));

		if (target == null) {
			request.setAttribute("errorMsg", "ユーザー情報が取得できませんでした。");
			response.sendRedirect(request.getContextPath() + "/userList");
			return;
		}

		request.setAttribute("targetUser", target);
		//編集画面へ遷移
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userEdit.jsp");
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
		
		// リクエストパラメータを取得 
		String editLoginId = request.getParameter("editLoginId");
		String editPassword = request.getParameter("editPassword");
		String editName = request.getParameter("editName");
		String editId = request.getParameter("editId");

		// バリデーション 
		Validation validation = new Validation();
		validation.isBlank("ログインID", editLoginId);
		validation.isBlank("パスワード", editPassword);
		validation.isBlank("お名前", editName);
		validation.length("ログインID", editLoginId, 1, 8);
		validation.length("パスワード", editPassword, 2, 10);
		validation.length("お名前", editName, 1, 10);

		// 入力値エラーがあった場合 
		if (validation.hasErrorMsg()) {
			request.setAttribute("errorMsg",
					validation.getErrorMsgList());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userEdit.jsp");
			rd.forward(request, response);
			return;
		} else {
			// 変更後の値をリクエストスコープへ保存して確認画面へ 
			request.setAttribute("userEditLoginId", editLoginId);
			request.setAttribute("userEditName", editName);
			request.setAttribute("userEditPassword", editPassword);
			request.setAttribute("editId", editId);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userEditConfirm.jsp");
			rd.forward(request, response);
		}
	}

}
