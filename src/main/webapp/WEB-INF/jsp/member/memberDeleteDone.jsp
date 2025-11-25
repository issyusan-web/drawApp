<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除完了</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- メンバー情報の削除完了JSP -->
<body>
	<p style="text-align: right">${user.name}様ログイン中</p>
	<div class="center">
		<c:if test="${msg != null}">
			<c:choose>
				<c:when test="${fn:contains(msg, '成功')}">
					<div class="success">
						<h1>${msg}</h1>
					</div>
				</c:when>
				<c:otherwise>
					<div class="error">
						<h1>${msg}</h1>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
	<p class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/admin">管理ページに戻る</a>
	</p>
</body>
</html>