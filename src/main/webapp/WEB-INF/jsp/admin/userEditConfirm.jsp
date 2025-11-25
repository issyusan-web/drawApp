<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>変更確認画面</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<!-- 管理者用会員情報変更確認画面 -->
<body>
	<h1 class="headline">変更内容確認</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">下記の内容でよろしければ、変更ボタンを押してください。</h2>
	<form
		action="${pageContext.request.contextPath}/UserEditConfirmController"
		method="post">
		<div class="center">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td><input type="hidden" name="userEditLoginId"
						value="${userEditLoginId}">${userEditLoginId}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="hidden" name="userEditPassword"
						value="${userEditPassword}">${userEditPassword}</td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type="hidden" name="userEditName"
						value="${userEditName}">${userEditName}</td>
				</tr>
			</table>
		</div>
		<p class="center">
			<input type="hidden" name="editId" value="${editId}"> <input
				type="submit" value="変更する">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>
</body>

</html>