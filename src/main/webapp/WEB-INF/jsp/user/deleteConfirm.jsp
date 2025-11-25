<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- ユーザー情報の削除確認画面 -->
<body>
	<h1 class="headline">会員情報削除確認画面</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">下記の内容でよろしければ、退会ボタンを押してください。</h2>
	<!-- 削除失敗時のエラーメッセージ表示 -->
	<c:if test="${not empty deleteError}">
		<div class="error">
			<p>${deleteError}</p>
		</div>
	</c:if>
	<h2 class="center">会員情報</h2>
	<form action="${pageContext.request.contextPath}/delete" method="post">
		<div class="center">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td>${user.loginId}</td>
				</tr>
				<tr>
					<th>お名前</th>
					<td>${user.name}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" class="passwordField"
						value="${user.password}" readonly>
						<button type="button" onclick="togglePassword()">表示</button></td>
				</tr>
			</table>
		</div>
		<p class="center">
			<input type="submit" value="退会する">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin"
			href="${pageContext.request.contextPath}/MyPageController">マイページに戻る</a>
	</p>

	<script src="${pageContext.request.contextPath}/js/pass.js"></script>

</body>

</html>