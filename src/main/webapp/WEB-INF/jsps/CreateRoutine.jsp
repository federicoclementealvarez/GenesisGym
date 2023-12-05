<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Routine'%>
<%@ page import='java.util.ArrayList'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.classless.min.css"
    />
    
 <link href="CreateRoutine.css" rel="stylesheet">
 
<title>Genesis Gym</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Nueva Rutina</h1>
		</article>
	</div>
	<form method="post" action="InsertNewRoutineServlet">
		<div class="inputContainer">
			<label><h4>Nombre de la rutina</h4></label>
      		<input id="dni" name="routineName" type="text" required autofocus>
		</div>
		 <div class="buttonContainer">
			<button type="submit"><h5 id="buttonText">Crear nueva rutina</h5></button>
		</div>
		<p>Los ejercicios podrán agregarse luego, en Mis Rutinas</p>
	</form>
</body>
</html>