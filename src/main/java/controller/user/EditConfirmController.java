package controller.user;

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

//変更内容の確認後、変更を実行するコントローラー。
@WebServlet("/editConfirm")
public class EditConfirmController extends HttpServlet {
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
		//入力情報の取得。
		String loginId = request.getParameter("editLoginId");
		String password = request.getParameter("editPassword");
		String name = request.getParameter("editName");

		// 更新後の会員情報をDomainに格納
		User user = (User) session.getAttribute("user");
		User newUser = new User(user.getId(),loginId, password, name,user.getAuth());

		UserEditService editService = new UserEditService();
		boolean result = editService.userEditDo(newUser);

		// 更新に成功した場合はresultにtrueが格納されている
		if (result) {
			//更新された情報をスコープに保存
			session.setAttribute("user", newUser);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/editDone.jsp");
			rd.forward(request, response);
		} else {
			// 会員情報の更新に失敗した場合はエラーメッセージを用意して編集画面へ戻す
			request.setAttribute("editError", "メンバー情報の更新に失敗しました。※すでに使われてる可能性あり");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp");
			rd.forward(request, response);
		}
	}

}
