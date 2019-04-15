<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<h1>Add a Student</h1>
	<c:url var="addStudentURL" value="/users/${userName}/${classId}/addStudent" />
	<form action="${addStudentURL}" method="POST">
		<label for="name">Student's Name</label>
		<input type="text" name="name">
		<label for="email">Student's Email</label>
		<input type="text" name="email">
		<label for="comments">Comments</label>
		<input type="text" name="comments">
		<input type="submit" name="addStudentSubmit" value="Submit">
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />