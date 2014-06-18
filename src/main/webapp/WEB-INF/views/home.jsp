<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<ul>
	<c:forEach items="${tasks}" var="task">
	    <li>${task.description} - ${task.assigneeName}</li>
	</c:forEach>
</ul>

</body>
</html>
