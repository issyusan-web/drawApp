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
<body>
	<h1 class="headline">変更内容確認</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">下記の内容でよろしければ、変更ボタンを押してください。</h2>
	<form action="${pageContext.request.contextPath}/editConfirm"
		method="post">
		<div class="container">
			<table border="1">
				<tr>
					<th>ユーザーID</th>
					<td><input type="hidden" name="editLoginId"
						value="${editLoginId}">${editLoginId}</td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="hidden" name="editPassword"
						value="${editPassword}">${editPassword}</td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><input type="hidden" name="editName" value="${editName}">${editName}</td>
				</tr>
			</table>
		</div>
		<p class="center">
			<input type="submit" value="変更する">
		</p>
	</form>
	<p class="center">
		<a class="returnadmin"
			href="${pageContext.request.contextPath}/MyPageController">マイページに戻る</a>
	</p>
</body>

</html>