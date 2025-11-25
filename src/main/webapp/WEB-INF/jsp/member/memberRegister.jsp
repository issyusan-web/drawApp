<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー登録</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- メンバー登録するJSP -->
<body>
	<h1 class="headline">メンバー登録</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<c:if test="${not empty msg}">
		<div class="error">
			<h1>${msg}</h1>
		</div>
	</c:if>

	<form action="${pageContext.request.contextPath}/memberRegister"
		method="post">
		<div class="container">
			<c:forEach var="i" begin="1" end="25" step="1">
				<div class="member-box">
					<p>${i}人目</p>
					<input type="text" name="name">
				</div>
			</c:forEach>
		</div>
		<p class="center">
			<input type="submit" value="登録">
		</p>
	</form>

	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>

</body>
</html>