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
    
 <link href="AvailableClasses.css" rel="stylesheet">
 
 <%ArrayList<Class> availableClasses = (ArrayList<Class>) session.getAttribute("availableClasses");%>
 
<title>Genesis Gym - Clases Disponibles</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Clases Disponibles</h1>
		</article>
	</div>
	<div>
		<%if(availableClasses == null || availableClasses.size() == 0){%>
			<div class="noClasses">
				<h3>No hay clases disponibles para tu plan en este momento...</h3>
			</div>
		<%}%>
		<%if(availableClasses != null && availableClasses.size() > 0){
		for(Class c: availableClasses){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=c.getActivity().getName()%></h3>
						</div>
						<form method="post" action="EnrollInClassServlet">
							<div class="cardButtonContainer">
								<button value="<%=c.getId()%>" name="classId" type="submit">Inscribirme</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer">
				  		<h5>Dia: </h5>
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
	<div class="buttonContainer">
		<button type="button" onclick="window.location.href='ManageClassesServlet'" style="background-color: #607d8b; border-color: #607d8b;"><h5 id="buttonText">Volver a Mis Clases</h5></button>
	</div>
</body>
</html>
