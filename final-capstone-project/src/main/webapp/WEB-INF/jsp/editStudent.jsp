<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<h1>Edit Student</h1>
	<c:url var="editStudentURL" value="/users/${userName}/${classId}/${studentId}" />
	<form:form action="${editStudentURL}" method="POST" modelAttribute="student">
		<c:set var="student" value="${student}" />
		<label for="name">Student's Name:</label>
		<form:input type="text" path="name" />
		<label for="email">Student's Email:</label>
		<form:input type="text" path="email" />
		<label for="comments">Comments:</label>
		<form:input type="text" path="comment" />
		<input type="submit" name="editStudentSubmit" value="Submit">
	</form:form>
	
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
	