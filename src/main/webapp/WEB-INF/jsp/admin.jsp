<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理ページ</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- 管理画面 -->
<body>
	<h1 class="headline">管理ページ</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<p class="center">
		<a href="${pageContext.request.contextPath}/memberRegister"><img
			src="${pageContext.request.contextPath}/img/Regist.png" alt="メンバー登録"></a>
		<a href="${pageContext.request.contextPath}/memberSelectDelete"><img
			src="${pageContext.request.contextPath}/img/Delete.png" alt="メンバー削除"></a>
		<a href="${pageContext.request.contextPath}/memberDelete"><img
			src="${pageContext.request.contextPath}/img/AllDelete.png"
			alt="メンバー全削除"></a>
	</p>
	<!-- authの値が 0なら一般ユーザー、1なら管理者 -->
	<c:choose>
		<c:when test="${user.auth == 0}">
			<p class="center">
				<a href="${pageContext.request.contextPath}/MyPageController"><img
					src="${pageContext.request.contextPath}/img/information.png"
					alt="マイページ"></a>
			</p>
		</c:when>
		<c:otherwise>
			<p class="center">
				<a href="${pageContext.request.contextPath}/userList"><img
					src="${pageContext.request.contextPath}/img/List.png" alt="一覧"></a>
			</p>
		</c:otherwise>
	</c:choose>

	<p class="center">
		<a href="${pageContext.request.contextPath}/memberSelectAllController"><img
			class="home" src="${pageContext.request.contextPath}/img/Home2.png"
			alt="ホームに戻る"></a>
	</p>

	<p style="text-align: right">
		<a class="returnadmin"
			href="${pageContext.request.contextPath}/logout">ログアウト</a>
	</p>
</body>
</html>