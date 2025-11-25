package controller.member;

import java.io.IOException;
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
import service.MemberDeleteService;
import service.MemberSelectService;
import service.MemberService;

//任意のメンバーを削除するためのコントローラー。
@WebServlet("/memberSelectDeleteConfirm")
public class MemberSelectDeleteConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//確認画面表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// ログイン中ユーザー取得
		User user = (User) session.getAttribute("user");

		//メンバー一覧から削除したいメンバーのIDを取得する。
		String memIdStr = request.getParameter("memId");
		// パラメータ検証
		if (memIdStr == null || !memIdStr.matches("\\d+")) {
			request.setAttribute("selectError", "不正なリクエストです。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDelete.jsp");
			rd.forward(request, response);
			return;
		}
		int memId = Integer.parseInt(memIdStr);

		MemberSelectService memberSelectService = new MemberSelectService();
		//IDを元に１件分の情報が格納されたdomainを取得する。
		Member mem = memberSelectService.findMemberById(memId);

		//情報が取得出来ていて、本人のメンバーかどうかのチェックもOKなら確認画面へ。出来なければメンバー一覧へ戻る。
		if (mem != null && mem.getUserId() == user.getId()) {
			//削除したいメンバーの情報をスコープへ格納。
			request.setAttribute("target", mem);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDeleteConfirm.jsp");
			rd.forward(request, response);
		} else {
			//メンバー情報が取得出来なければ,一覧ページに戻る。
			request.setAttribute("selectError", "情報が取得出来ませんでした。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDelete.jsp");
			rd.forward(request, response);
		}
	}

	//削除実行
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインチェック
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		// ログイン中ユーザー取得
		User user = (User) session.getAttribute("user");

		// パラメータ検証
		String memIdStr = request.getParameter("memId");
		if (memIdStr == null || !memIdStr.matches("\\d+")) {
			request.setAttribute("deleteError", "不正なリクエストです。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDelete.jsp");
			rd.forward(request, response);
			return;
		}
		int memId = Integer.parseInt(memIdStr);

		MemberSelectService memberSelectService = new MemberSelectService();
		Member mem = memberSelectService.findMemberById(memId);

		// 本人のメンバーじゃなければ削除させない
		if (mem == null || mem.getUserId() != user.getId()) {
			request.setAttribute("deleteError", "削除対象の情報が見つかりませんでした。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDelete.jsp");
			rd.forward(request, response);
			return;
		}

		MemberDeleteService memberDeleteService = new MemberDeleteService();
		// 取得したIDを元に、serviceで削除する
		int result = memberDeleteService.deleteMember(memId);

		//削除出来てれば、
		if (result == 1) {
			//このアノテーションは「開発者が型安全性を理解したうえでキャストしている」という宣言
			@SuppressWarnings("unchecked")
			List<Member> memberList = (List<Member>) session.getAttribute("memberList");
			//memberListがnullじゃないなら
			if (memberList != null) {
				MemberService memberService = new MemberService();
				Member dummy = new Member();
				dummy.setId(memId);
				//削除されたメンバーを含まないListを作成する。
				memberList = memberService.removeMemberFromList(memberList, dummy);
				session.setAttribute("memberList", memberList);
			}

			//削除完了画面へフォワードする。
			request.setAttribute("msg", "1件の削除に成功しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberDeleteDone.jsp");
			rd.forward(request, response);
		} else {
			// 削除に失敗した場合、エラーメッセージを用意して一覧ページに戻る。
			request.setAttribute("deleteError", "登録情報の削除に失敗しました。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/member/memberSelectDelete.jsp");
			rd.forward(request, response);
		}
	}
}
