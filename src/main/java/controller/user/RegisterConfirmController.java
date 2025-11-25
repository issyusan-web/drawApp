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

//新規登録を実行するコントローラー。
@WebServlet("/registerConfirm")
public class RegisterConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ユーザーが入力した情報を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		// 登録する会員情報を Domain に格納 
		User user = new User(loginId, password, name);
		UserRegisterService userRegister = new UserRegisterService();
		boolean result = userRegister.userEntryDo(user);

		// 登録に成功した場合は result に true が格納されている
		if (result) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/registerDone.jsp");
			rd.forward(request, response);
		} else {
			// 新規会員登録に失敗した場合はエラーメッセージを用意して会員登録画面へ戻す
			request.setAttribute("registerError", "新規会員登録に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp");
			rd.forward(request, response);
		}
	}

}
