package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserLoginService;

//ユーザーログインに関するコントローラー
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//index.jspから遷移するメソッド。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//login.jspへ遷移
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
	}

	//ユーザーの入力した情報をチェックして、登録されてるユーザーか確認する。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ユーザーが入力した情報を取得 
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		//ID、パスワードの入力チェック
		if (loginId == null || loginId.isEmpty() ||
				password == null || password.isEmpty()) {

			request.setAttribute("loginError", "ログインIDとパスワードを入力してください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
			return;
		}

		// UserLoginService の loginCheck メソッドにパラメータを渡す 
		UserLoginService loginService = new UserLoginService();
		User user = loginService.loginCheck(loginId, password);

		// ログイン成功した場合は
		if (user != null) {
			//セッションスコープに user を保存。
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			 response.sendRedirect(request.getContextPath() + "/memberSelectAllController");
			 
			// ログイン ID やパスワードが間違っていた場合は
		} else {
			//リクエストスコープにエラーメッセージを格納。
			request.setAttribute("loginError", "ログインID またはパスワードが間違っています。");
			//loginページに戻して、再入力を促す。
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			rd.forward(request, response);
		}
	}
}
