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
    
 <link href="ManageRoutines.css" rel="stylesheet">
 
 <%ArrayList<Routine> routines = (ArrayList<Routine>) session.getAttribute("routines");%>
 
<title>Genesis Gym</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Mis Rutinas</h1>
		</article>
	</div>
	<div>
		<%if(routines.size()==0){%>
			<div class="noRoutines">
				<h3>Aún no tienes rutinas...</h3>
			</div>
		<%}%>
		<%if(routines.size()>0){
		for(Routine r: routines){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=r.getName()%></h3>
						</div>
						<form method="post" action="ShowRoutineServlet">
							<div class="cardButtonContainer">
								<button type="submit">Ver</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer">
				  		<h5>Tipo de rutina: </h5>
				  		<div class="pContainer">
				  			<p class="data"><%=r.getType()%></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Cantidad de ejercicios: </h5>
				  		<div class="pContainer">
				  			<p class="data"><%=r.getExerciseQuantity()%></p>
				  		</div>
				  	</div>
				</article>
			</div>
		<%}}%>
	</div>
	<form method="post" action="CreateRoutineServlet">
		<div class="buttonContainer">
			<button type="submit"><h3 id="buttonPlus">+</h3><h5 id="buttonText">Crear nueva rutina</h5></button>
		</div>
	</form>
</body>
</html>