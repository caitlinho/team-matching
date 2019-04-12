<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<h1>Students</h1>
	
	<div class="studentNav">
		<c:url var="uploadStudents" value="/users/{userName}/{classId}/upload" />
		<a href="${uploadStudents}">Upload Student List</a>
		<c:url var="addStudent" value="/users/{userName}/{classId}/addStudent" />
		<a href="${addStudent}">Add Student to Class</a>
	</div>
	
	<table class="students">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Comments</th>
			<th>Active</th>
		</tr>
		<!-- for loop starts -->
			<tr>
				<td>
					<c:url var="student" value="/users/{userName}/{classId}/${forEachVariable.id}" />
					<a href="${student}">${forEachVariable.name}</a>
				</td>
				<td>
					<c:out value="${forEachVariable.email}" />
				</td>
				<td>
					<c:out value="${forEachVariable.comments}" />
				</td>
				<td>
					<c:if test="${forEachVariable.active} == true"> {
						<c:out value="Active" />
					</c:if>
					<c:if test="${forEachVariable.active} == false"> {
						<c:out value="Inactive" />
					</c:if>
				</td>
			</tr>
		<!-- for loop ends -->
	</table>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
