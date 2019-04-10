<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar" />

<div class="body">

	<form class="addClass">
		<c:out value="Class Name" />
		<input type="text" name="className">
	</form>

</div>

<c:import url="/WEB-INF/jsp/footer" />
