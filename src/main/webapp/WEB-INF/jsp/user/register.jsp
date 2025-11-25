<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
	<h1 class="headline">新規会員登録</h1>
	<!-- 入力値チェック時のエラーメッセージがあれば表示 -->
	<c:if test="${not empty errorMsg}">
		<ul>
			<c:forEach var="msg" items="${errorMsg}">
			<li class="error">${msg}</li>
			</c:forEach>
		</ul>
	</c:if>
	<c:if test="${not empty registerError}">
		<div class="error">
			<p>${registerError}</p>
		</div>
	</c:if>

	
	<h2 class="center">下記のフォームより、ユーザー情報をご登録ください</h2>
	<form action="${pageContext.request.contextPath}/register"
		method="post">
		<div class="center">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td><input type="text" name="loginId" placeholder="1～8 文字"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="text" name="password" placeholder="2～10 文字"></td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type="text" name="name" placeholder="1～10文字"></td>
				</tr>
			</table>
		</div>
		<p class="center">
			<input type="submit" value="登録">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/about">戻る</a>
	</p>
</body>
</html>