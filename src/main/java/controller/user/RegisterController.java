package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserRegisterService;
import validation.Validation;

//新規ユーザー登録するためのコントローラー。
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ログイン画面からここに飛んでくる。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// register.jsp へフォワード
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp");
		rd.forward(request, response);
	}

	//情報を入力したらここに飛んでくる。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータを取得 
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		// バリデーションで入力値チェック 
		Validation validation = new Validation();
		validation.isBlank("ユーザーID", loginId);
		validation.isBlank("パスワード", password);
		validation.isBlank("お名前", name);
		validation.length("ユーザーID", loginId, 1, 8);
		validation.length("パスワード", password, 2, 10);
		validation.length("お名前", name, 1, 10);

		// 入力値エラーがあった場合 
		if (validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp");
			rd.forward(request, response);
			return;
		}

		// ユーザー情報を Domain に格納 
		User user = new User(loginId, password, name);
		// 新規登録したいユーザーが DB に既に存在するかチェック 
		UserRegisterService registerService = new UserRegisterService();
		boolean result = registerService.userEntryConfirm(user);

		// ユーザーが存在しない場合は result に true が格納されている
		if (result) {
			// リクエストスコープに Domain を格納してフォワード 
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/registerConfirm.jsp");
			rd.forward(request, response);
		} else {
			// 既にユーザーが存在する場合はエラーメッセージを用意して新規登録画面へ戻す
			validation.addErrorMsg("入力いただいた ID「" + loginId + "」 はすでに使われています");
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp");
			rd.forward(request, response);
		}
	}
}