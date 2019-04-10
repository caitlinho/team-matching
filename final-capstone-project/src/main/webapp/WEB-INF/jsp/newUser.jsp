<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<c:url var="stylesheetHref" value="/css/site.css" />
<link rel="stylesheet" href="${stylesheetHref}">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
</head>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$.validator.addMethod('capitals', function(thing) {
							return thing.match(/[A-Z]/);
						});
						$("form")
								.validate(
										{

											rules : {
												userName : {
													required : true
												},
												password : {
													required : true,
													minlength : 15,
													capitals : true,
												},
												confirmPassword : {
													required : true,
													equalTo : "#password"
												}
											},
											messages : {
												password : {
													minlength : "Password too short, make it at least 15 characters",
													capitals : "Field must contain a capital letter",
												},
												confirmPassword : {
													equalTo : "Passwords do not match"
												}
											},
											errorClass : "error"
										});
					});
</script>
<div class="login">
	<c:url var="formAction" value="/users" />
	<form method="POST" action="${formAction}">
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />

		<div>
			<div class="form-group">
				<i class="fas fa-users"></i> <label for="userName">User
					Name: </label> <input type="text" id="userName" name="userName"
					placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<i class="fas fa-lock"></i> <label for="password">Password:
				</label> <input type="password" id="password" name="password"
					placeHolder="Password" class="form-control" />
			</div>
			<div class="form-group">
				<i class="fas fa-lock"></i> <label for="confirmPassword">Confirm
					Password: </label> <input type="password" id="confirmPassword"
					name="confirmPassword" placeHolder="Re-Type Password"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Create User</button>
		</div>

	</form>
</div>