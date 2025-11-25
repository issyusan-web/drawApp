<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報一覧</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
	<h1 class="headline">ユーザー情報一覧</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<!-- 汎用エラーメッセージ表示（不正なリクエストなど） -->
	<c:if test="${not empty errorMsg}">
		<div class="error">
			<p>${errorMsg}</p>
		</div>
	</c:if>

	<!-- 削除失敗時のエラーメッセージ表示 -->
	<c:if test="${not empty deleteError}">
		<div class="error">
			<p>${deleteError}</p>
		</div>
	</c:if>
	<div class="center">
		<table border=1>
			<tr>
				<th>ID</th>
				<th>ログインID</th>
				<th>パスワード</th>
				<th>名前</th>
				<th>権限</th>
				<th>ユーザー編集</th>
				<th>ユーザー削除</th>
			</tr>
			<c:forEach var="u" items="${userList}">
				<tr>
					<td>${u.id}</td>
					<td>${u.loginId}</td>
					<td><input type="password" class="passwordField"
						value="${u.password}" readonly>
						<button type="button" onclick="togglePassword(this)">表示</button></td>
					<td>${u.name}</td>
					<td>${u.auth}</td>

					<td>
						<form
							action="${pageContext.request.contextPath}/UserEditController"
							method="get">
							<input type="hidden" name="userId" value="${u.id}"> <input
								type="submit" value="編集">
						</form>
					</td>

					<td>
						<form
							action="${pageContext.request.contextPath}/UserDeleteController"
							method="get">
							<input type="hidden" name="userId" value="${u.id}"> <input
								type="submit" value="削除">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>

	<script src="${pageContext.request.contextPath}/js/pass.js"></script>
</body>
</html>