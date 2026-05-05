<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Plan'%>
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
    
 <link href="ManageAdminPlans.css" rel="stylesheet">
 
 <%ArrayList<Plan> plans = (ArrayList<Plan>) session.getAttribute("allAdminPlans");
   String error = request.getParameter("error");%>
 
<title>Genesis Gym - Gestión de Planes</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Gestión de Planes</h1>
		</article>
	</div>
	
	<%if(error != null && error.equals("used")){%>
		<div style="width: 80%; margin: auto; padding: 10px; background-color: #ffcdd2; color: #b71c1c; border-radius: 5px; text-align: center; margin-bottom: 20px;">
			No se puede eliminar el plan porque hay usuarios inscritos en él.
		</div>
	<%}%>

	<div>
		<%if(plans == null || plans.size() == 0){%>
			<div class="noData">
				<h3>No hay planes registrados.</h3>
			</div>
		<%}%>
		<%if(plans != null && plans.size() > 0){
		for(Plan p: plans){%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3><%=p.getName()%></h3>
						</div>
						<form method="post" action="DeletePlanServlet">
							<div class="cardButtonContainer">
								<input type="hidden" name="id" value="<%=p.getId()%>">
								<button type="submit" style="background-color: #d32f2f; border-color: #d32f2f;">Eliminar</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer">
				  		<h5>Tarifa mensual: </h5>
				  		<div class="pContainer">
				  			<p>$<%= String.format("%.2f", p.getRate()) %></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Actividades incluidas: </h5>
				  		<div class="pContainer">
				  			<p>
				  				<%for(int i=0; i<p.getActivities().size(); i++){%>
				  					<%=p.getActivities().get(i).getName()%><%= (i < p.getActivities().size()-1) ? ", " : "" %>
				  				<%}%>
				  			</p>
				  		</div>
				  	</div>
				</article>
			</div>
		<%}}%>
	</div>
	<form method="post" action="CreatePlanServlet">
		<div class="buttonContainer">
			<button type="submit"><h3 id="buttonPlus">+</h3><h5 id="buttonText">Nuevo plan</h5></button>
		</div>
	</form>
	<div class="buttonContainer" style="margin-top: 5%;">
		<button type="button" onclick="window.location.href='ValidateUserServlet'" style="background-color: #607d8b; border-color: #607d8b;"><h5 id="buttonText">Volver al inicio</h5></button>
	</div>
</body>
</html>
