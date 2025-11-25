package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 管理者ページでログアウトリンクが押された際に呼ばれる。
	// セッションを破棄し、新しい状態でホーム画面を再表示する。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションオブジェクト取得（無ければnull）
		HttpSession session = request.getSession(false);
		// セッションが存在すれば破棄し、ログイン画面にリダイレクトする。
		if (session != null) {
		session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/about");
	}
}