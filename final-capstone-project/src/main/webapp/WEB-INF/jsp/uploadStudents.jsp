<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />


<head>
<c:url var="Image" value="/img/student.jpg" />
<body style="background-image: url(${Image});">
<c:url var="stylesheetHref" value="/css/uploadStudent.css" />
<link rel="stylesheet" href="${stylesheetHref}">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>


<div class="body"> 
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    File to upload: <input type="file" name="file" >
    <br />
    <input class="input"type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}" />
    <input type="hidden" name="test" value="blah" />
    <br />
    <br />
    <input class="btn btn-primary btn-block btn-large"type="submit" value="Upload">
</form>
<c:if test="${not empty message}">
    ${message} 
</c:if>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp" />