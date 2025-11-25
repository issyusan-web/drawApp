package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//管理ページへ遷移させるコントローラー
@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//home.jspから管理ページへのリンクが押されるとここに飛んでくる。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションが存在すれば返すが、なければ null を返す
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			// すでにログイン済みなら、管理ページへ
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin.jsp");
			rd.forward(request, response);
		} else {
			// ログインしていなければログイン画面へ
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
}
