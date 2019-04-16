<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:url var="stylesheetHref" value="/css/navbar.css" />
<link rel="stylesheet" href="${stylesheetHref}">

	<c:import url="/WEB-INF/jsp/header.jsp" />

	<nav class="navBar">
		<ul id="main-ul">
			<li style="list-style-type:none;"><c:url var="dashboard" value="/users/{userName}/dashboard" />
			<a href="${dashboard}">Dashboard</a></li>
			<li style="list-style-type:none;"><c:url var="acceptedTeams" value="/users/{userName}/teams" />
			<a href="${acceptedTeams}">Teams</a></li>
			<li style="list-style-type:none;" class="lastLi"><c:url var="logout" value="/logout" />
			<a href="${logout}">Logout</a></li>
		</ul>
	</nav>
	
