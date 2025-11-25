<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>What about DRAW APP?</title>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
</head>
<body>
	<h1 class="headline">ようこそDRAW APPへ</h1>
	<h2 class="center">DRAW APPとは？</h2>
	<p class="center">このアプリでは、登録したメンバーからランダムで1人ずつ抽選を行い、順番決めをすることができます</p>
	<p class="center">発表順を決めたり、質問に答えてくれる人を指名するのにうってつけ✨</p>
	<p class="center">登録がまだの人は、下の新規登録ボタンをクリック！</p>
	<p class="center">登録済みの人は、ログインページへGO!</p>
	<div class="center">
		<a class="returnadmin" href="${pageContext.request.contextPath}/login">ログインページへ</a>
		<a class="returnadmin"
			href="${pageContext.request.contextPath}/register">新規会員登録はこちら</a>
	</div>
</body>
</html>