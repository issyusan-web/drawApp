<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録確認</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<body>
	<h1 class="headline">登録内容確認</h1>
	<h2 class="center">下記の内容でよろしければ、登録ボタンを押してください。</h2>
	<form action="${pageContext.request.contextPath}/registerConfirm"
		method="post">

		<div class="center">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td><input type="hidden" name="loginId"
						value="${user.loginId}">${user.loginId}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="hidden" name="password"
						value="${user.password}">${user.password}</td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type="hidden" name="name" value="${user.name}">${user.name}</td>
				</tr>
			</table>
		</div>
		<p class="center">
			<input type="submit" value="登録">
		</p>
		<p class="center">
			<a class="returnadmin"
				href="${pageContext.request.contextPath}/about">戻る</a>
		</p>
	</form>
</body>
</html>