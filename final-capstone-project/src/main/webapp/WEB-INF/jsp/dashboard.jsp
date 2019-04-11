<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<<<<<<< HEAD
<c:url var="stylesheetHref" value="/css/dashboard.css" />
<link rel="stylesheet" href="${stylesheetHref}">

<c:import url="/WEB-INF/jsp/navbar" />
=======
<c:import url="/WEB-INF/jsp/navbar.jsp" />
>>>>>>> 03cee19efbf6617ef0d599b4b806ee150989527b

<div class="body">
	<c:forEach items="${allClasses}" var="singleClass">
		<div class="singleClass">
			<c:url var="classDetails" value="/users/{currentUser.userName}/{classId}" />
			<c:param name="classId" value="${allClasses.classId}" />
			<a href="${classDetails}">${singleClass.name}</a>
		</div>
	</c:forEach>
	<form class="addClass">
		<c:url var="addClassURL" value="/users/{currentUser.userName}/addClass" />
		<a href="${addClassURL}">Add Class</a>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />