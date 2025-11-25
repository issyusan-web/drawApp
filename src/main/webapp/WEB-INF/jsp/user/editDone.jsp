<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>変更完了画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
	<h1 class="headline">変更完了</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">登録内容の変更が完了しました</h2>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/MyPageController">マイページに戻る</a>
	</p>
</body>
</html>