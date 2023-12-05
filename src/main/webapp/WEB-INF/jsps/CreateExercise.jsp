<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.ExerciseType'%>
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
    
 <link href="CreateExercise.css" rel="stylesheet">
 
<title>Genesis Gym</title>

 <%ArrayList<ExerciseType> exTypes = (ArrayList<ExerciseType>) session.getAttribute("exerciseTypes");%>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Nuevo Ejercicio</h1>
		</article>
	</div>
	<%if(exTypes.size()==0) {%>
		<div class="noPlan">
			<article>
				<h1 id="ups">Ups...</h1>
				<h3>Parece que tu plan no incluye ningún ejercicio</h3>
			</article>
		</div>
	<%}%>
	<%if(exTypes.size()>0) {%>
		<form method="post" action="InsertNewExerciseServlet">
			<div class="inputContainer" id="topInputContainer">
				<label><h4>Cantidad de series</h4></label>
				<div class="numberInputContainer">
					<input name="setQuantity" type="number" required autofocus>
				</div>
			</div>
			<div class="inputContainer">
				<label><h4>Cantidad de repeticiones</h4></label>
				<div class="numberInputContainer">
					<input name="repetitionQuantity" type="number" required>
				</div>
			</div>
			<div class="inputContainer">
				<label><h4>Tiempo entre repeticiones</h4></label>
				<div class="numberInputContainer" id="restTimeContainer">
					<input name="restTimeBetweenSetsInSeconds" type="number" required>
					<div id="middleDiv"></div>
					<div id="secondsContainer">
						<h5>segundos</h5>
					</div>
				</div>
			<div class="inputContainer" id="selectContainer">
				<label><h4>Tipo de ejercicio</h4></label>
				<select name="exerciseTypeId" id="selectExType" required>
					<%for(ExerciseType et :exTypes) {%>
						<option value="<%=et.getId()%>"><%= et.getName()%></option>
					<%}%>
				</select>
			</div>
			</div>
			 <div class="buttonContainer">
				<button type="submit"><h5 id="buttonText">Crear ejercicio</h5></button>
			</div>
		</form>
	<%}%>
</body>
</html>