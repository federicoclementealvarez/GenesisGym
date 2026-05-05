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
    
 <link href="CreatePlan.css" rel="stylesheet">
 
<title>Genesis Gym - Nuevo Plan</title>

 <%ArrayList<Activity> activities = (ArrayList<Activity>) session.getAttribute("allActivities");%>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Nuevo Plan</h1>
		</article>
	</div>
	
	<form method="post" action="InsertNewPlanServlet">
		<div class="inputContainer" id="topInputContainer">
			<label><h4>Nombre del Plan</h4></label>
			<div class="textInputContainer">
				<input name="name" type="text" placeholder="Ej: Plan Musculación" required autofocus>
			</div>
		</div>
		
		<div class="inputContainer">
			<label><h4>Tarifa Mensual</h4></label>
			<div class="textInputContainer">
				<input name="rate" type="number" step="0.01" placeholder="Ej: 5000.00" required>
			</div>
		</div>
		
		<div class="inputContainer">
			<label><h4>Actividades Incluidas</h4></label>
			<div class="checkboxContainer">
				<%for(Activity a: activities){%>
					<div style="display: flex; align-items: center; margin-bottom: 5px;">
						<input type="checkbox" name="idActivity" value="<%=a.getId()%>" id="act_<%=a.getId()%>" style="margin-right: 10px; margin-bottom: 0;">
						<label for="act_<%=a.getId()%>" style="margin-bottom: 0; color: #555;"><%=a.getName()%></label>
					</div>
				<%}%>
			</div>
		</div>

		<div class="buttonContainer">
			<button type="submit"><h5 id="buttonText">Crear plan</h5></button>
		</div>
		
		<div class="buttonContainer" style="margin-top: 5%;">
			<button type="button" onclick="window.location.href='ManageAdminPlansServlet'" style="background-color: #607d8b; border-color: #607d8b;">
				<h5 id="buttonText">Cancelar</h5>
			</button>
		</div>
	</form>
</body>
</html>
