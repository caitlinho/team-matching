<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar" />

<div class="body">
	<c:forEach items="${allClasses}" var="singleClass">
		<div class="singleClass">
			<c:out value="${singleClass.name}" />
		</div>
	</c:forEach>
	<form class="addClass">
		<button type="submit" class="classButton">Add Class+</button>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer" />
