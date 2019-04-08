<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar" />

<div class="body">
	<h1>${classname CHANGE THIS} Students</h1>
	
	<button>Add Student</button>
	
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
					<c:url var="student" value="/student${forEachVariable}" />
					<a href="${student}">${forEachVariable.name}</a>
				</td>
				<td>
					<c:out value="${forEachVariable.email}" />
				</td>
				<td>
					<c:out value="${forEachVariable.comments}" />
				</td>
				<td>
					<c:out value="${forEachVariable.active}" />
				</td>
			</tr>
		<!-- for loop ends -->
	</table>
</div>

<c:import url="/WEB-INF/jsp/footer" />
