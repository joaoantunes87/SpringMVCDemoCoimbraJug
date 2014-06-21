<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Todo Page</title>
</head>
<body>
	<h1>Task</h1>
	<ul>
		<li>Description: ${task.description}</li>
		<li>Assignee: ${task.assigneeName} </li>
	</ul>
</body>
</html>
