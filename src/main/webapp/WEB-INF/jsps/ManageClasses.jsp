<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Class'%>
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
    
 <link href="ManageClasses.css" rel="stylesheet">
 
 <%ArrayList<Class> classes = (ArrayList<Class>) session.getAttribute("classes");%>
 
<title>Genesis Gym - Mis Clases</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Mis Clases</h1>
		</article>
	</div>
	<div>
		<%if(classes == null || classes.size() == 0){%>
			<div class="noClasses">
				<h3>Aun no estás inscrito en ninguna clase...</h3>
			</div>
		<%}%>
		<%if(classes != null && classes.size() > 0){
		for(Class c: classes){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=c.getActivity().getName()%></h3>
						</div>
						<form method="post" action="DeleteEnrollmentServlet">
							<div class="cardButtonContainer">
								<button value="<%=c.getId()%>" name="classId" type="submit" style="background-color: #d32f2f; border-color: #d32f2f;">Cancelar</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer">
				  		<h5>Día: </h5>
				  		<div class="pContainer">
				  			<p><%=c.getDay()%></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer">
				  		<h5>Horario: </h5>
				  		<div class="pContainer">
				  			<p><%=c.getBeginHour()%> - <%=c.getEndHour()%></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Profesor: </h5>
				  		<div class="pContainer">
				  			<p><%=c.getTeacher().getName()%> <%=c.getTeacher().getLastName()%></p>
				  		</div>
				  	</div>
				</article>
			</div>
		<%}}%>
	</div>
	<form method="post" action="ShowAvailableClassesServlet">
		<div class="buttonContainer">
			<button type="submit"><h3 id="buttonPlus">+</h3><h5 id="buttonText">Inscribirme a una clase</h5></button>
		</div>
	</form>
	<div class="buttonContainer" style="margin-top: 5%;">
		<button type="button" onclick="window.location.href='ValidateUserServlet'" style="background-color: #607d8b; border-color: #607d8b;"><h5 id="buttonText">Volver al inicio</h5></button>
	</div>
</body>
</html>
