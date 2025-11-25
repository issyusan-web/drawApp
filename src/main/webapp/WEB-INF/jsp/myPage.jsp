<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイページ</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- ユーザー情報の編集、削除をするページ -->
<body>
	<h1 class="headline">マイページ</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<div class="center">
		<table border=1>
			<tr>
				<th>名前</th>
				<td>${user.name}</td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type="password" class="passwordField"
					value="${user.password}" readonly>
					<button type="button" onclick="togglePassword(this)">表示</button></td>
			</tr>
			<tr>
				<th>ログインID</th>
				<td>${user.loginId}</td>
			</tr>

		</table>
	</div>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/edit">ユーザー情報の編集</a>
	</p>
	<p class="center">
		<a class="returnadmin"
			href="${pageContext.request.contextPath}/delete">退会する方はこちら</a>
	</p>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>

	<script src="${pageContext.request.contextPath}/js/pass.js"></script>

</body>
</html>