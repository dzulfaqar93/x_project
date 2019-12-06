<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Learn MVC</title>
<script
	src="${pageContext.request.contextPath}/angularJs/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script>
<script src="${pageContext.request.contextPath}/angularJs/app.js"></script>
<!-- <script src="../service/MainService.js"></script> -->
<script
	src="${pageContext.request.contextPath}/angularJs/factory/list.factory.js"></script>
<script
	src="${pageContext.request.contextPath}/angularJs/controller/list.controller.js"></script>


<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/assets/js/jquery-2.2.4.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
</head>

<body data-ng-app="crudApp">
	<div data-ng-controller="insertController" class="container">

		<h2>{{header}}</h2>
		
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-left">ID</label>
			<div class="col-sm-8">
				<input type="text" data-ng-model="user.noId"
					class="form-control input-sm col-xs-5 col-sm-5" />
			</div>
		</div>
		<br>
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-left">NAMA</label>
			<div class="col-sm-8">
				<input type="text" data-ng-model="user.nama"
					class="form-control input-sm col-xs-5 col-sm-5" />
			</div>
		</div>
		<br>
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-left">EMAIL</label>
			<div class="col-sm-8">
				<input type="text" data-ng-model="user.email"
					class="form-control input-sm col-xs-5 col-sm-5" />
			</div>
		</div>
		<br>
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-left">ALAMAT</label>
			<div class="col-sm-8">
				<input type="text" data-ng-model="user.alamat"
					class="form-control input-sm col-xs-5 col-sm-5" />
			</div>
		</div>
		<br>
		<div class="form-group">
			<label class="col-sm-4 control-label no-padding-left">NO HP</label>
			<div class="col-sm-8">
				<input type="text" data-ng-model="user.noHp"
					class="form-control input-sm col-xs-5 col-sm-5" />
			</div>
		</div>
		
		<button class="btn btn-info" data-ng-click="insUser()">Insert</button>
	</div>
</body>
</html>