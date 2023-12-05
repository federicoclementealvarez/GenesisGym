<%@ page import='entities.People'%>
<%@ page import='entities.Plan'%>
<%@ page import='entities.Activity'%>
<%@ page import='java.util.ArrayList'%>
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
<title>Genesis Gym</title>

<%People p = (People) session.getAttribute("user");
ArrayList<Plan> plans =(ArrayList<Plan>) request.getAttribute("plans");
%>

  <link href="ChoosePlan.css" rel="stylesheet">
    
  </head>

  <body>
    <form action="ChoosePlanServlet" method="post">
      <img src="images/Logo.png">
      <div class="pContainer">
      	<h2>Escoja un plan</h2>
     </div>
     <fieldset>
	     <div id="inputContainer">
		      <%for(Plan plan:plans){%>
		      		<div class="cardContainer">
				 		<div class="articleContainer">
				 			<article> 
						 		<div class="cardTitleContainer">
						 			<h3><%=plan.getName()%></h3>
						 			<div class="center">
						 				<h4 id="rate">$<%=plan.getRate()%>/mes</h4>
						 			</div>
						 		</div>
						 		<div class="includedActs">
						 				<p>Actividades que incluye:</p>
						 		</div>
						 		<ul>
							 		<% for(Activity act:plan.getActivities()){%>
							 			<li><%=act.getName()%></li>
							 		<% }%>
						 		</ul>
				 			</article>
				 		</div>
				 		<div class="radioButtonContainer">
				 			<input type="radio" id="radioButton" name="planId" value=<%=plan.getId()%> required>
				 		</div>
				 	</div>
			  <% }%>
	      </div>
      </fieldset>
      <div id="buttonContainer">
      	<button type="submit">Registrarse</button>
      </div>
    </form>
  </body>
</html>