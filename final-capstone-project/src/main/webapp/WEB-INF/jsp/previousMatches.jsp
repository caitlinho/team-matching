<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">

	<h1>Previous Matches</h1>
	
	<table class="table">
		<tr>
			<th>Class</th>
			<th>Week</th>
			<th>Team Members</th>
		</tr>
		<c:forEach var="team" items="${newMatches}">
			<tr>
				<td>${InstructorClass.name}</td>
				<td>${team.week}</td>
				<td>${team.studentId1}</td>
				<td>${team.studentId2}</td>
			</tr>
		</c:forEach>
	</table>

</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />