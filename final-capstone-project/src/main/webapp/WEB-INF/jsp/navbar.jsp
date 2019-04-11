<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<c:import url="/WEB-INF/jsp/header" />

	<nav class="nav">
		<div class="navDiv">
			<c:url var="dashboard" value="/" />
			<a href="${dashboard}">Dashboard</a>
			<c:url var="acceptedTeams" value="/teams" />
			<a href="${acceptedTeams}">Teams</a>
			<c:url var="logout" value="/logout" />
			<a href="${logout}">Logout</a>
		</div>
	</nav>