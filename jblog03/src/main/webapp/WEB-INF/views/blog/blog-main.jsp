<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title}</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>		
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>	
					</c:otherwise>
				</c:choose>
				
				<li><a href="${pageContext.request.contextPath}/${blogVo.userId}/admin">블로그 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/${blogVo.userId}">내 블로그</a></li>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<c:set value="${map.post }" var="postvo" />
					<h4>${postvo.title }</h4>
					<p>
						${postvo.contents }
					<p>
				</div>
				<ul class="blog-list">
				<c:forEach items="${map.postlist }" var = "postlistvo" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/${blogVo.userId}/${postlistvo.categoryNo}/${postlistvo.no }">${postlistvo.title }</a> <span>${postlistvo.regDate }</span>	</li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${map.categorylist }" var = "categoryvo" varStatus="status">
				<li><a href="${pageContext.request.contextPath}/${blogVo.userId }/${categoryvo.no }">${categoryvo.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>