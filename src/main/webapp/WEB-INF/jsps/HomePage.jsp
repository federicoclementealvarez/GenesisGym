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
else{
	userTypeText = "Profesor";
}%>
</head>
<body>
	<div class="mainCardContainer">
		<article>
		  	<header><h2 id="cardHeader"><%= p.getName()%> <%= p.getLastName()%></h2></header>
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
			<form>
				<article id="classCard">
					<h4 class="insideText">Mis Clases</h4>
					<button type="submit" class="insideButton">Ver</button>
				</article>
			</form>
		</div>
	</div>
	<div class="cardContainer">
		<div class="eachCardContainer">
			<form>
				<article id="planCard">
					<h4 class="insideText">Mi Plan</h4>
					<button type="submit" class="insideButton">Ver</button>
				</article>
			</form>
		</div>
		<div class="middleDiv">
		</div>
		<div class="eachCardContainer">
			<form>
				<article id="payCard">
					<h4 class="insideText">Mis Cuotas</h4>
					<button type="submit" class="insideButton">Ver</button>
				</article>
			</form>
		</div>
	</div>
</body>
</html>