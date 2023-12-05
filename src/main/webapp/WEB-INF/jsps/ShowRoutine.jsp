<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Routine'%>
<%@ page import='entities.Exercise'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.classless.min.css"
    />
    
 <link href="ShowRoutine.css" rel="stylesheet">
 
 <%Routine r = (Routine) session.getAttribute("routine");
 int i=1;%>
 
<title>Genesis Gym</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title"><%=r.getName()%></h1>
		</article>
	</div>
	<div>
		<%if(r.getExercises().size()==0){%>
			<div class="noRoutines">
				<h3>Aún no tienes Ejercicios...</h3>
			</div>
		<%}%>
		<%if(r.getExercises().size()>0){
		for(Exercise e: r.getExercises()){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=i%>) <%=e.getExerciseType().getName()%></h3>
						</div>
						<form method="post" action="DeleteExerciseServlet">
							<div class="cardButtonContainer">
								<button value=<%=e.getId()%> name="exerciseId" type="submit" id="dropButton">Quitar</button>
							</div>
						</form>
					</div>
					<div class="cardDataContainer" id="topContainer">
				  		<div class="pContainer">
				  			<p class="data" id="exerciseTypeDesc"><%=e.getExerciseType().getDescription()%></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer">
				  		<h5>Cantidad de series: </h5>
				  		<div class="pContainer">
				  			<h5 class="data"><%=e.getSetQuantity()%></h5>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer">
				  		<h5>Repeticiones por serie: </h5>
				  		<div class="pContainer">
				  			<h5 class="data"><%=e.getRepetitionQuantity()%></h5>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Descanso entre series: </h5>
				  		<div class="pContainer">
				  			<h5 class="data"><%=e.getRestTimeBetweenSetsInSeconds()%> segs</h5>
				  		</div>
				  	</div>
				</article>
			</div>
		<%i++;  }}%>
	</div>
	<form method="post" action="CreateExerciseServlet">
		<div class="buttonContainer">
			<button type="submit"><h3 id="buttonPlus">+</h3><h5 id="buttonText">Agregar Ejercicio</h5></button>
		</div>
	</form>
	<form method="post" action="DeleteRoutineServlet">
		<div class="bottomButtonContainer">
			<button type="submit" id="dropButton"><h5 id="buttonText">Borrar Rutina</h5></button>
		</div>
	</form>
</body>
</html>