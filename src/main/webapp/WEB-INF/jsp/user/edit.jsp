<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報編集画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
	<h1 class="headline">ユーザー情報編集画面</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>

	<!-- 入力値チェック時のエラーメッセージ表示 -->
	<c:if test="${not empty errorMsg}">
		<ul>
			<c:forEach var="msg" items="${errorMsg}">
				<li class="error">${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	<!-- 更新失敗時のエラーメッセージ表示 -->
	<c:if test="${not empty editError}">
		<div class="error">
			<p>${editError}</p>
		</div>
	</c:if>
	<h2 class="center">変更内容を入力してください</h2>
	<form action="${pageContext.request.contextPath}/edit" method="post">
		<div class="center">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td><input type="text" name="editLoginId" placeholder="1～8 文字"
						value="${user.loginId}"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="editPassword"
						placeholder="2～10 文字" value="${user.password}"></td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type="text" name="editName" placeholder="1～10 文字"
						value="${user.name}"></td>
				</tr>

			</table>
		</div>
		<p class="center">
			<input type="submit" value="変更する（確認画面へ）">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin"
			href="${pageContext.request.contextPath}/MyPageController">マイページに戻る</a>
	</p>
</body>
</html>