package controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//削除するメンバーを選択するページへ遷移するコントローラー。
@WebServlet("/memberSelectDelete")
public class MemberSelectDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//admin.jspでメンバー削除のリンクが押されるとここに飛んでくる。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		//メンバー一覧を表示して、そこから削除したいメンバーを選ぶ画面にフォワードする。
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDelete.jsp");
		rd.forward(request, response);
	}
}
