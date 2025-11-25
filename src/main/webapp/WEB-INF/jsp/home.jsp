<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DRAW APP</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>

<!-- ホーム画面 -->
<body>
	<!-- メンバー情報が取得できなかったらエラーメッセージを表示 -->
	<c:if test="${not empty errorMSG}">
		<div class="error">
			<h1>${errorMSG}</h1>
		</div>
	</c:if>

	<h2>～己の順番を待ちわびし者達～</h2>
	<!-- DBに登録されてるメンバーを表示 -->
	<div class="member-container">
		<c:forEach var="member" items="${memberList}">
			<div class="member-icon">${member.name}さん</div>
		</c:forEach>
	</div>

	<hr>

	<!-- 抽選で選ばれたメンバーを表示 -->
	<div>
		<c:choose>
			<c:when test="${not empty choiceMember}">
				<div class="choice-center">
					<div class="member-choice">${choiceMember.name}さん</div>
				</div>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty choiceError}">
					<div style="color: red;">
						<p>${choiceError}</p>
					</div>
				</c:if>
			</c:otherwise>
		</c:choose>
	</div>

	<p class="center">
		<!-- ランダムコントローラーへ飛ぶ -->
		<a href="${pageContext.request.contextPath}/randomController"><img
			src="${pageContext.request.contextPath}/img/Choice.png" alt="抽選する"></a>
		<!-- リセットコントローラーへ飛ぶ -->
		<a href="${pageContext.request.contextPath}/memberSelectAllController"><img
			src="${pageContext.request.contextPath}/img/Reset.png" alt="リセット"></a>
	</p>

	<p class="center">✨管理ページで「己の順番を待ちわびし者達」に追加、削除ができるよ✨</p>
	<div class="center">
		<form action="${pageContext.request.contextPath}/admin" method="get">
			<input type="submit" value="管理ページへ">
		</form>
	</div>

	<hr>

	<!-- 抽選が終わったメンバーを表示 -->
	<h2>～終わって解放されし者達～</h2>
	<div class="finished-area">
		<c:if test="${not empty finishedMemberList}">
			<c:forEach var="end" items="${finishedMemberList}">
				<div class="finished-mover">${end.name}さん</div>
			</c:forEach>
		</c:if>
	</div>


	<!-- クラッカー用 -->
	<c:if test="${not empty choiceMember}">
		<script src="${pageContext.request.contextPath}/js/confetti.js"></script>
	</c:if>

	<!-- 終了者バウンド移動用 -->
	<script src="${pageContext.request.contextPath}/js/mover.js"></script>


</body>
</html>