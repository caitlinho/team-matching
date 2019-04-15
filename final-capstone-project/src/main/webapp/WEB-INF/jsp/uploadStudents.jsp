<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<<<<<<< HEAD
	<c:url var="uploadFileURL" value="/users/${userName}/${classId}/upload" />
	<form method="POST" action="${uploadFile}" enctype="multipart/form-data">
	    File to upload: <input type="file" name="file" >
	    <br />
	    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
	    <input type="hidden" name="test" value="blah" />
	    <br />
	    <br />
	    <input type="submit" value="Upload">
	</form>
=======
 <form method="POST" action="${uploadFile}" enctype="multipart/form-data">
    File to upload: <input type="file" name="file" >
    <br />
    <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
    <input type="hidden" name="test" value="blah" />
    <br />
    <br />
    <input type="submit" value="Upload">
</form>
>>>>>>> 32ce65feffaf624267c8039c048d071ee9a721e2
<c:if test="${not empty message}">
    ${message} 
</c:if>

<c:import url="/WEB-INF/jsp/footer.jsp" />