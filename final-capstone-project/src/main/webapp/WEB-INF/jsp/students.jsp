<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />
<head>
<c:url var="stylesheetHref" value="/css/students.css" />
<link rel="stylesheet" href="${stylesheetHref}">
<c:url var="Image" value="/img/blackboard.jpg" />
<body style="background-image: url(${Image});">
</head>

<div class="body">
	<h1>Students</h1>

	<div class="navBody">
		<div class="studentNav">
			<c:url var="uploadStudents"
				value="/users/${userName}/${classId}/upload" />
			<a href="${uploadStudents}">Upload Student List</a>
		</div>
		<div class="studentNav">
			<c:url var="addStudent"
				value="/users/${userName}/${classId}/addStudent" />
			<a href="${addStudent}">Add Student to Class</a>
		</div>
	</div>

	<!-- <div> -->
	<table class="students" border="1">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Comments</th>
			<th>Active</th>
		</tr>
		<!-- </div> -->

		<div class="rows">
			<c:forEach var="student" items="${studentsByClass}">
				<tr>
					<td><c:url var="studentUrl"
							value="/users/${userName}/${classId}/${student.studentId}" /> <a
						href="${studentUrl}">${student.name}</a></td>
					<td><c:out value="${student.email}" /></td>
					<td><c:out value="${student.comment}" /></td>
					<td><c:if test="${student.active} == true"> {
						<c:out value="Active" />
						</c:if> <c:if test="${student.active} == false"> {
						<c:out value="Inactive" />
						</c:if></td>
				</tr>
			</c:forEach>
	</table>
</div>



</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
