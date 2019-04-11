<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<c:forEach items="${allClasses}" var="singleClass">
		<div class="singleClass">
			<c:url var="classDetails" value="/users/{currentUser.userName}/{classId}" />
			<a href="${classDetails}">${singleClass.name}</a>
		</div>
	</c:forEach>
	<form class="addClass">
		<c:url var="addClassURL" value="/users/{currentUser.userName}/addClass" />
		<a href="${addClassURL}">Add Class</a>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />