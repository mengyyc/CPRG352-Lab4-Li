<%-- 
    Document   : viewnote
    Created on : 29-Sep-2021, 12:46:30 PM
    Author     : lixia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>View Note Page</title>
	</head>
	<body>
		<h1>Simple Note Keeper</h1>
		<h2>View Note</h2>
		<h3 class="bold">Title:</h3>${note.title}</p>
		<h3 class="bold">Contents:</h3><p>${note.content}</p>
		<a href="note?edit">Edit</a>
	</body>
</html>
