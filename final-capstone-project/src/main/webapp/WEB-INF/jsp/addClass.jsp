<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	
	<c:url var="addClassURL" value="/users/{userName}/addClass" />
	<form action="${addClassURL}" method="POST" modelAttribute="addClass">
		<c:out value="Class Name" />
		<input type="text" name="name">
		<button type="submit" name="addClassSubmit">Submit</button>
	</form>

</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />
