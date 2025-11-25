<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メンバー削除</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- スコープ内の全メンバー情報を取得し、削除するメンバーを選べるJSP -->
<body>
	<h1 class="headline">メンバー削除</h1>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<h2 class="center">どのメンバーを削除しますか？</h2>
	<c:if test="${not empty selectError}">
		<div class="error">
			<p>${selectError}</p>
		</div>
	</c:if>
	<c:if test="${not empty deleteError}">
		<div class="error">
			<p>${deleteError}</p>
		</div>
	</c:if>

	<c:choose>
		<c:when test="${not empty memberList}">
			<div class="container">
				<c:forEach var="member" items="${memberList}">
					<div class="member-box">
						<p>名前：${member.name}</p>
						<form
							action="${pageContext.request.contextPath}/memberSelectDeleteConfirm"
							method="get">
							<input type="hidden" name="memId" value="${member.id}"> <input
								type="submit" value="削除">
						</form>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:otherwise>
			<p class="center error">削除できるメンバーがいません。</p>
		</c:otherwise>
	</c:choose>

	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>

</body>
</html>