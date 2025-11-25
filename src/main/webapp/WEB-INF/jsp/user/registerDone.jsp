<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録完了</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
	<h1 class="headline">ユーザー登録完了</h1>
	<h2 class="center">ユーザー登録が完了しました</h2>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/login">ログイン画面に戻る</a>
	</p>
</body>
</html>