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
	<div data-ng-controller="crudController" class="container">

		<h2>{{header}}</h2>
		<div class="container">
			<table class="table table-striped">
				<tr>
					<th>No</th>
					<th>Name</th>
					<th>Email</th>
					<th>Alamat</th>
					<th>No HP</th>
					<th>Action</th>
				</tr>

				<tr data-ng-repeat="u in users">
					<td>{{u.noId}}</td>
					<td>{{u.nama}}</td>
					<td>{{u.email}}</td>
					<td>{{u.alamat}}</td>
					<td>{{u.noHp}}</td>
					<td>
						<div class="btn-group" role="group" aria-label="Basic example">
							<button class="btn btn-info" data-ng-click="detail(u.noId)">Detail</button>
							<a data-ng-click="deleteUser(u.noId)" class="btn btn-danger"
								role="button">Delete</a> 
							<a data-ng-click="editUser(u.noId)"
								class="btn btn-success" role="button">Edit</a>
						</div>
					</td>
				</tr>
			</table>

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

		</div>
		<a href="${pageContext.request.contextPath}/master/insert"
			class="btn btn-success" role="button">Insert</a> <a
			data-ng-click="editUser()" class="btn btn-success" role="button">Update</a>
	</div>
</body>
</html>