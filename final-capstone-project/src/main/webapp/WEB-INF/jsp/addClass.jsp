<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />


<head>
<c:url var="stylesheetHref" value="/css/addClass.css" />
<link rel="stylesheet" href="${stylesheetHref}">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<c:url var="Image" value="/img/team.jpg" />
<body style="background-image: url(${Image});">
</head>



<div class="login">
	<h1 >Add A Class</h1>
	<c:url var="addClassURL" value="/users/{userName}/addClass" />
	<form action="${addClassURL}" method="POST" modelAttribute="addClass">
		<%-- <c:out value="Class Name" /> --%>
		<i class="fas fa-chalkboard-teacher"></i>
     	<label for="addClass input" class="lable">Class Name: </label> 
		<input type="text" name="name">
		<button type="submit" name="addClassSubmit" class="btn btn-primary btn-block btn-large">Submit</button>
		<label for="weekOfMatch">Week Number: </label>
		<input type="text" name="weekOfMatch">
		<label for="size">Size of Group: </label>
		<select name="size">
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<label for="countOfMatch">Identical Pair Limit</label>
		<select name="countOfMatch">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
