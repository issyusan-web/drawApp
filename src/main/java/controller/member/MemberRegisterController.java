package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.User;
import service.MemberRegisterService;
import service.MemberSelectService;
import validation.Validation;

//メンバーを登録するためのコントローラー。
@WebServlet("/memberRegister")
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//管理ページでメンバー登録のリンクが押されたらここに飛んでくる。
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		// メンバー登録フォームへ遷移。
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberRegister.jsp");
		rd.forward(request, response);
	}

	//入力された名前をDBに保存する。
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//ユーザーIDの取得→これでどのユーザーが操作してるか決まる。
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		//入力された名前たちを取得。複数の値が取得できるので、配列で受け取る。
		String[] names = request.getParameterValues("name");
		//配列をListに変換
		List<String> nameList = new ArrayList<>();
		if (names != null) {
			nameList = new ArrayList<>(Arrays.asList(names));
		}

		//入力された名前が保存されたListの中身をバリデーションする。
		Validation validation = new Validation();
		nameList = validation.isBlank(nameList);

		// 空入力のみだった場合
		if (nameList.isEmpty()) {
			request.setAttribute("msg", "名前が1件も入力されていません。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberRegister.jsp");
			rd.forward(request, response);
			return; // これ以上の処理を止める
		}

		//DAOに渡すためのList作成
		List<Member> members = new ArrayList<Member>();

		//domainに名前とユーザーIDを格納する
		for (String name : nameList) {
			//ユーザーが入力した名前とユーザーIDをそれぞれセットし、Listに追加
			Member member = new Member(name, userId);
			members.add(member);
		}

		//INSERTするためのserviceを呼び出す。
		MemberRegisterService memberRegisterService = new MemberRegisterService();
		//IDと名前がセットされたdomainインスタンスたちが保存されたListを渡す。
		//成功した件数が返ってくる。
		//String result = mrs.registerMembers(list);
		int count = memberRegisterService.registerMembers(members);
		//リクエストスコープに件数を格納
		if (count > 0) {
			request.setAttribute("msg", count + "件登録成功");
		} else {
			request.setAttribute("msg", "全件登録失敗");
		}

		//更新された情報をDBから取得する
		MemberSelectService memberSelectService = new MemberSelectService();
		List<Member> memberList = memberSelectService.findAllMembers(userId);
		session.setAttribute("memberList", memberList);

		//登録結果表示JSPにフォワードする。
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberRegisterDone.jsp");
		rd.forward(request, response);
	}
}
