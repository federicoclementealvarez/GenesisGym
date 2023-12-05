<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Plan'%>
<%@ page import='entities.Activity'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.classless.min.css"
    />
    
 <link href="ManagePlans.css" rel="stylesheet">
 
<title>Genesis Gym</title>

<%Plan plan = (Plan) session.getAttribute("plan");%>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Mi Plan</h1>
		</article>
	</div>
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
	 		<% for(Activity act: plan.getActivities()){%>
	 			<li><%=act.getName()%></li>
	 		<% }%>
 		</ul>
		</article>
	</div>
</body>
</html>