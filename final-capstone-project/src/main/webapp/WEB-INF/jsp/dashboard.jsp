<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="stylesheetHref" value="/css/dashboard.css" />
<link rel="stylesheet" href="${stylesheetHref}">

<c:import url="/WEB-INF/jsp/navbar" />

<div class="body">
	<c:forEach items="${allClasses}" var="singleClass">
		<div class="singleClass">
			<c:url var="classDetails" value="/users/{userName}/{classId}" />
			<a href="${classDetails}">${singleClass.name}</a>
		</div>
	</c:forEach>
	<form class="addClass">
		<c:url var="addClassURL" value="/users/{userName}/addClass" />
		<a href="${addClassURL}">Add Class +</a>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer" />
