<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/navbar" />

<div class="body">
	<h1>Upload Student List</h1>
	<h3>Please upload file type .csv</h3>
	
	<form action="uploadStudents" method="post" enctype="multipart/form-data">
	<input type="file" name="studentFile" size="50" />
	<input type="submit" value="Upload" />
	</form>
</div>

<c:import url="/WEB-INF/jsp/footer" />