<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false"%>
<html>
<head>
<title>ToDos Index</title>
</head>
<body>
	<h1>Tasks</h1>

	<c:if test="${fn:length(companies) eq 0}">
   		<p>No Tasks<p>
	</c:if>
	<ul>
		<c:forEach items="${tasks}" var="task">
			<li>${task.description}- ${task.assigneeName}</li>
		</c:forEach>
	</ul>
	
	<h2>Create Task</h2>
	<form action="/todos" method="POST">
		<label for="description">Description:</label>
		<input type="text" name="description" id="description"/>
		<label for="assigneeName">Assignee Name:</label>
		<input type="text" name="assigneeName" id="assigneeName"/>
		<input type="submit" value="Add"/>		
	</form>

</body>
</html>
