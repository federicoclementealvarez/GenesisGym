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
    
 <link href="TaughtClasses.css" rel="stylesheet">
 
 <%ArrayList<Class> taughtClasses = (ArrayList<Class>) session.getAttribute("taughtClasses");
   String error = request.getParameter("error");%>
 
<title>Genesis Gym - Clases Enseñadas</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Clases Enseñadas</h1>
		</article>
	</div>
	
	<%if(error != null && error.equals("has_attendants")){%>
		<div style="width: 80%; margin: auto; padding: 10px; background-color: #ffcdd2; color: #b71c1c; border-radius: 5px; text-align: center; margin-bottom: 20px;">
			No se puede eliminar una clase que tiene alumnos inscritos.
		</div>
	<%}%>

	<div>
		<%if(taughtClasses == null || taughtClasses.size() == 0){%>
			<div class="noClasses">
				<h3>Aún no tienes clases asignadas como profesor.</h3>
			</div>
		<%}%>
		<%if(taughtClasses != null && taughtClasses.size() > 0){
		for(Class c: taughtClasses){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=c.getActivity().getName()%></h3>
						</div>
						<form method="post" action="DeleteClassServlet">
							<div class="cardButtonContainer">
								<input type="hidden" name="classId" value="<%=c.getId()%>">
								<button type="submit" style="background-color: #d32f2f; border-color: #d32f2f;">Eliminar</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer">
				  		<h5>Día: </h5>
				  		<div class="pContainer">
				  			<p><%=c.getDay()%></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Horario: </h5>
				  		<div class="pContainer">
				  			<p><%=c.getBeginHour()%> - <%=c.getEndHour()%></p>
				  		</div>
				  	</div>
				</article>
			</div>
		<%}}%>
	</div>
	<form method="post" action="CreateClassServlet">
		<div class="buttonContainer">
			<button type="submit"><h3 id="buttonPlus">+</h3><h5 id="buttonText">Crear nueva clase</h5></button>
		</div>
	</form>
	<div class="buttonContainer" style="margin-top: 5%;">
		<form method="post" action="ExportTaughtClassesPDFServlet" style="margin-bottom: 10px;">
			<button type="submit" style="background-color: #f44336; border-color: #f44336; width: 100%; display: flex; justify-content: center; align-items: center;">
				<h5 id="buttonText" style="margin: 0;">Exportar a PDF</h5>
			</button>
		</form>
		<button type="button" onclick="window.location.href='ValidateUserServlet'" style="background-color: #607d8b; border-color: #607d8b; width: 100%; display: flex; justify-content: center; align-items: center;">
			<h5 id="buttonText" style="margin: 0;">Volver al inicio</h5>
		</button>
	</div>
</body>
</html>
