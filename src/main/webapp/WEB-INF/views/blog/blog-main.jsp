<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%

	pageContext.setAttribute("newLine", "\n");

%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header-blog.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<c:if test="${not empty post }">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>
						${fn:replace(post.contents, newLine, '<br/>')}
					<p>	
				</div>
				</c:if>
				<c:if test="${not empty postList }">
				<ul class="blog-list">
					<c:forEach items="${postList }" var="postvo">
						<li><a href="${pageContext.request.contextPath}/${id}/${postvo.categoryNo}/${postvo.no}">${postvo.title}</a> <span>${postvo.writtenDate }</span>	</li>
					</c:forEach>
				</ul>
				</c:if>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${vo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${list }"  var="categoryvo">
				<li><a href="${pageContext.request.contextPath}/${id}/${categoryvo.no}">${categoryvo.name} }</a></li>
				</c:forEach>	
			</ul>
		</div>
		<c:import url="/WEB-INF/views/include/footer-blog.jsp"></c:import>
	</div>
</body>
</html>