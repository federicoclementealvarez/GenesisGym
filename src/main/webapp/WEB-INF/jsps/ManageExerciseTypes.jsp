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
    
 <link href="ManageExerciseTypes.css" rel="stylesheet">
 
 <%ArrayList<ExerciseType> exTypes = (ArrayList<ExerciseType>) session.getAttribute("allExerciseTypes");
   String error = request.getParameter("error");%>
 
<title>Genesis Gym - Tipos de Ejercicio</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Tipos de Ejercicio</h1>
		</article>
	</div>
	
	<%if(error != null && error.equals("used")){%>
		<div style="width: 80%; margin: auto; padding: 10px; background-color: #ffcdd2; color: #b71c1c; border-radius: 5px; text-align: center; margin-bottom: 20px;">
			No se puede eliminar el tipo de ejercicio porque est� siendo usado en rutinas activas.
		</div>
	<%}%>

	<div>
		<%if(exTypes == null || exTypes.size() == 0){%>
			<div class="noData">
				<h3>No hay tipos de ejercicio registrados.</h3>
			</div>
		<%}%>
		<%if(exTypes != null && exTypes.size() > 0){
		for(ExerciseType et: exTypes){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=et.getName()%></h3>
						</div>
						<form method="post" action="DeleteExerciseTypeServlet">
							<div class="cardButtonContainer">
								<input type="hidden" name="id" value="<%=et.getId()%>">
								<button type="submit" style="background-color: #d32f2f; border-color: #d32f2f;">Eliminar</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Descripci�n: </h5>
				  		<div class="pContainer">
				  			<p><%=et.getDescription()%></p>
				  		</div>
				  	</div>
				</article>
			</div>
		<%}}%>
	</div>
	<form method="post" action="CreateExerciseTypeServlet">
		<div class="buttonContainer">
			<button type="submit"><h3 id="buttonPlus">+</h3><h5 id="buttonText">Nuevo tipo de ejercicio</h5></button>
		</div>
	</form>
	<div class="buttonContainer" style="margin-top: 5%;">
		<button type="button" onclick="window.location.href='ValidateUserServlet'" style="background-color: #607d8b; border-color: #607d8b;"><h5 id="buttonText">Volver al inicio</h5></button>
	</div>
</body>
</html>
