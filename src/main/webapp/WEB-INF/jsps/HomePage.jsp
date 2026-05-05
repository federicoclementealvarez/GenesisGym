<%@ page import='entities.People'%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.classless.min.css"
    />
    
 <link href="HomePage.css" rel="stylesheet">
<title>Genesis Gym</title>
<%People p = (People) session.getAttribute("user");
String userTypeText=null;
if(p.getType().equals("client")){
	userTypeText = "Cliente independiente";
}
else if(p.getType().equals("teacher")){
	userTypeText = "Profesor";
}
else if(p.getType().equals("admin")){
	userTypeText = "Administrador";
}%>
</head>
<body>
	<div class="mainCardContainer">
		<article>
		  	<header>
		  		<div style="display: flex; justify-content: space-between; align-items: center;">
		  			<div style="flex-grow: 1; text-align: center;">
		  				<h2 id="cardHeader" style="margin-bottom: 0;"><%= p.getName()%> <%= p.getLastName()%></h2>
		  			</div>
		  			<form action="LogoutServlet" method="post" style="margin: 0;">
		  				<button type="submit" style="background-color: #f44336; border-color: #f44336; padding: 5px 10px; font-size: 0.8rem; margin: 0;">Cerrar sesión</button>
		  			</form>
		  		</div>
		  	</header>
		  	<div class="cardDataContainer">
		  		<h4>Tipo de usuario</h4>
		  		<p class="data"><%= userTypeText%></p>
		  	</div>
			<%if(p.getTeacher()!=null) {%>
				<div class="cardDataContainer">
			  		<h4>Nombre del profesor</h4>
			  		<p class="data"><%=p.getTeacher().getName()%> <%=p.getTeacher().getLastName()%></p>
			  	</div>
			  	<div class="cardDataContainer">
			  		<h4>Teléfono del profesor</h4>
			  		<p class="data"><%=p.getTeacher().getPhoneNumber()%></p>
			  	</div>
			<%}%>
		</article>
	</div>
	
	<%if(p.getType().equals("admin")){%>
		<div class="cardContainer">
			<div class="eachCardContainer">
				<form action="ManageExerciseTypesServlet" method="post">
					<article id="routCard">
						<h4 class="insideText">Tipos de ejercicio</h4>
						<button type="submit" class="insideButton">Ver</button>
					</article>
				</form>
			</div>
			<div class="middleDiv">
			</div>
			<div class="eachCardContainer">
				<form action="ManageAdminPlansServlet" method="post">
					<article id="planCard">
						<h4 class="insideText">Planes</h4>
						<button type="submit" class="insideButton">Ver</button>
					</article>
				</form>
			</div>
		</div>
	<%} else {%>
		<div class="cardContainer">
			<div class="eachCardContainer">
				<form action="ManageRoutinesServlet" method="post">
					<article id="routCard">
						<h4 class="insideText">Mis Rutinas</h4>
						<button type="submit" class="insideButton">Ver</button>
					</article>
				</form>
			</div>
			<div class="middleDiv">
			</div>
			<div class="eachCardContainer">
				<form action="ManageClassesServlet" method="post">
					<article id="classCard">
						<h4 class="insideText">Mis Clases</h4>
						<button type="submit" class="insideButton">Ver</button>
					</article>
				</form>
			</div>
		</div>
		<div class="cardContainer">
			<div class="eachCardContainer">
				<form action="ManagePlansServlet" method="post">
					<article id="planCard">
						<h4 class="insideText">Mi Plan</h4>
						<button type="submit" class="insideButton">Ver</button>
					</article>
				</form>
			</div>
			<div class="middleDiv">
			</div>
			<div class="eachCardContainer">
					<form action="ManageFeesServlet" method="post">
						<article id="payCard">
							<h4 class="insideText">Mis Cuotas</h4>
							<button type="submit" class="insideButton">Ver</button>
						</article>
					</form>
				</div>
		</div>
		<%if(p.getType().equals("teacher")){%>
			<div class="cardContainer">
				<div class="eachCardContainer">
					<form action="ManageTaughtClassesServlet" method="post">
						<article id="taughtCard">
							<h4 class="insideText">Clases Enseñadas</h4>
							<button type="submit" class="insideButton">Ver</button>
						</article>
					</form>
				</div>
			</div>
		<%}%>
	<%}%>
</body>
</html>
