<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>削除完了画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<body>
	<h1 class="headline">会員情報削除完了</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">会員情報の削除が完了しました</h2>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>
</body>

</html>