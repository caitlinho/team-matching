<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">

	<h1>Generated Matches</h1>
	
	<table class="table">
		<tr>
			<th>Class</th>
			<th>Week</th>
			<th>Team Members</th>
		</tr>
		<c:forEach var="team" items="${forEachVariable}">
			<tr>
				<td>${InstructorClass.name}</td>
				<td>${team.week}</td>
				<td>${team.NAMES}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:url var="regenerateMatchesURL" value="/users/{userName}/{classId}/pairs" />
	<form action="${regenerateMatchesURL}" method="POST">
		<button type="submit" name="regenerateMatchesSubmit">Re-Match</button>
	</form>
	
	<c:url var="acceptMatchesURL" value="/users/{userName}/{classId}/pairs/accept" />
	<form action="${acceptMatchesURL}" method="POST">
		<button type="submit" name="acceptMatchesSubmit">Accept</button>
	</form>

</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />