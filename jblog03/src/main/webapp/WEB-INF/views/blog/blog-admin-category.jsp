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
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${blogVo.userId }/admin">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${blogVo.userId }/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="categorycount" value="${map.categorycnt }"/>
		      		<c:forEach items="${map.categorylist }" var = "categoryvo" varStatus="status">
		      			<tr>
						<td>${categoryvo.no }</td>
						<td>${categoryvo.name }</td>
						<td>${categorycount[status.index] }</td>
						<td>${categoryvo.description }</td>
						<td><a href="${pageContext.servletContext.contextPath}/${blogVo.userId}/admin/category/delete/${categoryvo.no}">
						<c:if test="${categorycount[status.index] eq 0}">
						<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
						</c:if>
					</tr>  
		      		</c:forEach>				  
				</table>
      			
      			<form action="${pageContext.request.contextPath }/${blogVo.userId}/admin/category/insert" method="post">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<input type="hidden" name = "blogId" value = "${blogVo.userId }"> 
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table>
		      	</form> 
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>