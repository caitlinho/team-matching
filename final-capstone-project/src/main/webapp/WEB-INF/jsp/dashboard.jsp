<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<c:url var="stylesheetHref" value="/css/dashboard.css" />
<link rel="stylesheet" href="${stylesheetHref}">
</head>

<c:import url="/WEB-INF/jsp/navbar.jsp" />


<div>
	<c:forEach items="${allClasses}" var="singleClass">
		<div class="singleClass">
			<c:url var="classDetails" value="/users/${currentUser.userName}/${singleClass.classId}" />
			<a href="${classDetails}">${singleClass.name}</a>
		</div>
	</c:forEach>
	<div class="login">
	<h1 >Add A Class</h1>
	<form class="addClass btn btn-primary btn-block btn-large">
		<c:url var="addClassURL" value="/users/${currentUser.userName}/addClass" />
		<a href="${addClassURL}">Add Class</a>
	</form>
	</div>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />