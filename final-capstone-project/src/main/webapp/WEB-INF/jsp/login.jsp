<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
<c:url var="stylesheetHref" value="/css/site.css" />
<link rel="stylesheet" href="${stylesheetHref}">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>


<script type="text/javascript">
	$(document).ready(function () {
	
		$("form").validate({
			
			rules : {
				userName : {
					required : true
				},
				password : {
					required : true
				}
			},
			messages : {			
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>

<div class="login">
	<h2 >MATCHTASTIC!</h2>
	<div>
		<c:url var="formAction" value="/" />
		<form method="POST" action="${formAction}">
		
			<div class="form-group">
			<i class="fas fa-users"></i>
				<label for="userName input">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			
			<div class="form-group">
			<i class="fas fa-lock"></i>
				<label for="password">   Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			
			<button type="submit" class="btn">Login</button>
		</form>
	</div>
</div>
