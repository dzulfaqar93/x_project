<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <link href="${pageContext.request.contextPath}/static/simple.css" type = "text/css" rel = "stylesheet"> --%>
<title>Recruitment</title>

<link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" type = "text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/assets/js/jquery-2.2.4.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/angularJs/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/angularJs/angular.min.js.map"></script>

</head>
<body>
	<div class="container">

		<c:choose>
			<c:when test="${empty masterUser}">
				<span class="align-middle"></span>
				<div align="center">
					<p align="center">There's Currently No Data Exists</p>
					<p align="center">Please Create by Clicking the ADD Button</p>
				</div>
			</c:when>
			<c:otherwise>
				<nav class="navbar navbar-light bg-light"> <a
					class="navbar-brand">User Data</a>
				<form class="form-inline">
					<input action="${pageContext.request.contextPath}/list"
						class="form-control mr-sm-2" type="text" name="find"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
				</nav>
				<div class="container">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Nama</th>
								<th>Email</th>
								<th>Action</th>
							</tr>
						</thead>
						<c:forEach items="${masterUser}" var="pdk">
							<tbody>
								<tr>
									<td>${pdk.nama}</td>
									<td>${pdk.email}</td>
									<td>
										<div class="btn-group" role="group" aria-label="Basic example">
											<a
												href="${pageContext.request.contextPath}/detail/${pdk.noId}"
												class="btn btn-info" role="button">Detail</a> <a
												href="${pageContext.request.contextPath}/delete/${pdk.noId}"
												class="btn btn-danger" role="button">Delete</a>
										</div>
									</td>
								</tr>
							</tbody>
						</c:forEach>
						
						<%-- <tbody>
							<tr ng-repeat="user in ${masterUser}">
								<td>{{user.nama}}</td>
								<td>{{user.email}}</td>
							</tr>
						</tbody> --%>
						
					</table>
				</div>
			</c:otherwise>
		</c:choose>
		<a href="${pageContext.request.contextPath}/add"
			class="btn btn-primary btn-lg btn-block" role="button">ADD</a>
	</div>

</body>
</html>