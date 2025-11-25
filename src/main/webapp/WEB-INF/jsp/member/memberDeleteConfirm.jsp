<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全件削除確認</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- メンバーを全件削除していいか確認するJSP -->
<body>
	<h1 class="headline">全件削除確認</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">メンバーを全件削除します、よろしいですか？</h2>
	<form action="${pageContext.request.contextPath}/memberDelete"
		method="post">
		<p class="center">
			<input type="submit" value="削除">
		</p>
	</form>

	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>

</body>
</html>