package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;
import service.UserSelectService;

//全ユーザーを表示するためのコントローラー。
@WebServlet("/userList")
public class UserList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		//全ユーザー取得
		UserSelectService userSelectService = new UserSelectService();
		List<UserDTO> userList = userSelectService.getAllUsers();
		
		session.setAttribute("userList", userList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/userAll.jsp");
		rd.forward(request, response);
	}
}
