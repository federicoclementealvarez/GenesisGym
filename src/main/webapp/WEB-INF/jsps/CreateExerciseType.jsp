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
    
 <link href="CreateExerciseType.css" rel="stylesheet">
 
<title>Genesis Gym - Nuevo Tipo de Ejercicio</title>

 <%ArrayList<Activity> activities = (ArrayList<Activity>) session.getAttribute("nonTeacheableActivities");%>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Nuevo Tipo de Ejercicio</h1>
		</article>
	</div>
	
	<form method="post" action="InsertNewExerciseTypeServlet">
		<div class="inputContainer" id="topInputContainer">
			<label><h4>Nombre</h4></label>
			<div class="textInputContainer">
				<input name="name" type="text" placeholder="Ej: Press de Banca" required autofocus>
			</div>
		</div>
		
		<div class="inputContainer">
			<label><h4>Descripción</h4></label>
			<div class="textInputContainer">
				<input name="description" type="text" placeholder="Breve descripción del ejercicio" required>
			</div>
		</div>
		
		<div class="inputContainer" id="selectContainer">
			<label><h4>Actividad Asociada</h4></label>
			<div class="selectInputWrapper">
				<select name="idActivity" id="idActivity" required>
					<%for(Activity a: activities){%>
						<option value="<%=a.getId()%>"><%=a.getName()%></option>
					<%}%>
				</select>
			</div>
		</div>

		<div class="buttonContainer">
			<button type="submit"><h5 id="buttonText">Crear tipo de ejercicio</h5></button>
		</div>
		
		<div class="buttonContainer" style="margin-top: 5%;">
			<button type="button" onclick="window.location.href='ManageExerciseTypesServlet'" style="background-color: #607d8b; border-color: #607d8b;">
				<h5 id="buttonText">Cancelar</h5>
			</button>
		</div>
	</form>
</body>
</html>
