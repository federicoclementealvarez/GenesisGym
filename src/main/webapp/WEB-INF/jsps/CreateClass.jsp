<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Activity'%>
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
    
 <link href="CreateClass.css" rel="stylesheet">
 
<title>Genesis Gym</title>

 <%ArrayList<Activity> activities = (ArrayList<Activity>) session.getAttribute("activities");%>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Crear Nueva Clase</h1>
		</article>
	</div>
	
	<form method="post" action="InsertNewClassServlet">
		<div class="inputContainer" id="topInputContainer">
			<label><h4>Día/s de la semana</h4></label>
			<div class="textInputContainer">
				<input name="day" type="text" placeholder="Ej: Lunes" required autofocus>
			</div>
		</div>
		
		<div class="inputContainer">
			<label><h4>Hora de inicio</h4></label>
			<div class="timeInputContainer">
				<input type="time" name="beginHour" id="beginHour" required>
			</div>
		</div>
		
		<div class="inputContainer">
			<label><h4>Hora de fin</h4></label>
			<div class="timeInputContainer">
				<input type="time" name="endHour" id="endHour" required>
			</div>
		</div>
		
		<div class="inputContainer" id="selectContainer">
			<label><h4>Actividad</h4></label>
			<div class="selectInputWrapper">
				<select name="idActivity" id="idActivity" required>
					<%for(Activity a: activities){%>
						<option value="<%=a.getId()%>"><%=a.getName()%></option>
					<%}%>
				</select>
			</div>
		</div>

		<div class="buttonContainer">
			<button type="submit"><h5 id="buttonText">Crear clase</h5></button>
		</div>
		
		<div class="buttonContainer" style="margin-top: 5%;">
			<button type="button" onclick="window.location.href='ManageTaughtClassesServlet'" style="background-color: #607d8b; border-color: #607d8b;">
				<h5 id="buttonText">Cancelar</h5>
			</button>
		</div>
	</form>
</body>
</html>
