<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar.jsp" />

<div class="body">
	<c:url var="generateMatch" value="/users/{userName}/{classId}/pairs" />
	<form action="${generateMatch}" method="POST">
		<label for="weekOfMatch">Week Number: </label>
		<input type="text" name="weekOfMatch">
		<label for="size">Size of Group: </label>
		<select name="size">
			<option value="2">2</option>
			<option value="3">3</option>
		</select>
		<label for="countOfMatch">Identical Pair Limit</label>
		<select name="countOfMatch">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select>
		<button type="submit" name="generateMatches Submit">Generate Matches</button>
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />