<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">

<title>削除完了画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- ユーザー情報の削除完了画面 -->
<body>
	<h1 class="headline">会員情報削除完了</h1>
	<h2 class="center">会員情報の削除が完了しました</h2>
	<div class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/login">ログイン画面に戻る</a>
	</div>
</body>

</html>