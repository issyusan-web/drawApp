<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- URLにアクセスするとまずはこのjspに来る。LoginControllerへ移動 -->
<%
// フォワードでもOK（サーブレットで初期化したいならフォワード推奨）
request.getRequestDispatcher("/about").forward(request, response);

// もしくはリダイレクト
// response.sendRedirect(request.getContextPath() + "/memberSelectAllController");
%>
