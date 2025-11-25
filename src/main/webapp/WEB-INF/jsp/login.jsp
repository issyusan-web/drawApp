<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- ユーザーログイン画面 -->
<body>
	<h1 class="headline">ログイン</h1>
	<!-- IDやパスワードが間違ってたらエラーメッセージを表示 -->
	<c:if test="${loginError != null}">
		<div class="error">
			<p>${loginError}</p>
		</div>
	</c:if>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<div class="center">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td><input type="text" name="loginId"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
		</div>
		<p class="center">
			<input type="submit" value="ログイン">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/about">戻る</a>
	</p>
</body>

</html>