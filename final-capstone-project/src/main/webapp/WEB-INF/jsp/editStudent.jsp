<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<h1>Edit Student</h1>
	<c:url var="editStudentURL" value="/users/${userName}/${classId}/${studentId}" />
	<form action="${editStudentURL}" method="POST">
		<label for="name">Student's Name</label>
		<input type="text" name="name" value="${student.name}">
		<label for="email">Student's Email</label>
		<input type="text" name="email" value="${student.email}">
		<label for="comments">Comments</label>
		<input type="text" name="comments" value="${student.comment}">
		<input type="submit" name="editStudentSubmit" value="Submit">
	</form>
	
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
	