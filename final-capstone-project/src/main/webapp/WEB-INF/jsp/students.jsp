<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<h1>Students</h1>
	
	<div class="studentNav">
		<c:url var="uploadStudents" value="/users/${userName}/${classId}/upload" />
		<a href="${uploadStudents}">Upload Student List</a>
		<c:url var="addStudent" value="/users/${userName}/${classId}/addStudent" />
		<a href="${addStudent}">Add Student to Class</a>
	</div>
	
	<table class="students">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Comments</th>
			<th>Active</th>
		</tr>
		<c:forEach var="student" items="${studentsByClass}">
			<tr>
				<td>
					<c:url var="student" value="/users/${userName}/${classId}/${student.studentId}" />
					<a href="${student}">${student.name}</a>
				</td>
				<td>
					<c:out value="${student.email}" />
				</td>
				<td>
					<c:out value="${student.comment}" />
				</td>
				<td>
					<c:if test="${student.active} == true"> {
						<c:out value="Active" />
					</c:if>
					<c:if test="${student.active} == false"> {
						<c:out value="Inactive" />
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
