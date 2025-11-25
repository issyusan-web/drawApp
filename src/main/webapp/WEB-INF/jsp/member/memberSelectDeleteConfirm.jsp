<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除確認画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- 削除対象を表示して、最終確認をするJSP -->
<body>
	<h1 class="headline">削除確認</h1>
	<h2 class="center">以下のメンバーを削除します。いいですか？</h2>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<div class="center">
		<table border="1">
			<tr style="font-size: 25px;">
				<th>名前</th>
				<td>${target.name}</td>
			</tr>
		</table>
	</div>

	<form
		action="${pageContext.request.contextPath}/memberSelectDeleteConfirm"
		method="post">
		<p class="center">
			<input type="hidden" name="memId" value="${target.id}"> <input
				type="submit" value="削除">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>
</body>
</html>