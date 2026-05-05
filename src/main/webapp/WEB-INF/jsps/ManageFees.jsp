<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import='entities.Fee'%>
<%@ page import='java.util.ArrayList'%>
<%@ page import='java.time.LocalDate'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.classless.min.css"
    />
    
 <link href="ManageFees.css" rel="stylesheet">
 
 <%ArrayList<Fee> unpaidFees = (ArrayList<Fee>) session.getAttribute("unpaidFees");
   LocalDate today = LocalDate.now();%>
 
<title>Genesis Gym - Mis Cuotas</title>
</head>
<body>
	<div>
		<article id="topCard">
			<h1 class="title">Mis Cuotas</h1>
		</article>
	</div>
	<div>
		<%if(unpaidFees == null || unpaidFees.size() == 0){%>
			<div class="noFees">
				<h3>No tienes cuotas pendientes de pago. ¡Estás al día!</h3>
			</div>
		<%}%>
		<%if(unpaidFees != null && unpaidFees.size() > 0){
		for(Fee f: unpaidFees){
			boolean overdue = f.getDueDate().isBefore(today);
		%>
			<div class="cardContainer">
				<article>
					<div class="cardTopContainer">
						<div class="cardNameContainer">
							<h3 style="<%= overdue ? "color: #d32f2f;" : "" %>">Vencimiento: <%=f.getDueDate()%></h3>
						</div>
						<form method="post" action="PayFeeServlet">
							<div class="cardButtonContainer">
								<input type="hidden" name="dueDate" value="<%=f.getDueDate()%>">
								<button type="submit">Marcar pagado</button>
							</div>
						</form>
					</div>
				  	<div class="cardDataContainer">
				  		<h5>Monto: </h5>
				  		<div class="pContainer">
				  			<p>$<%= String.format("%.2f", f.getAmount()) %></p>
				  		</div>
				  	</div>
				  	<div class="cardDataContainer" id="bottomContainer">
				  		<h5>Estado: </h5>
				  		<div class="pContainer">
				  			<p><%= overdue ? "ATRASADA" : "PENDIENTE" %></p>
				  		</div>
				  	</div>
				</article>
			</div>
		<%}}%>
	</div>
	<div class="buttonContainer">
		<button type="button" onclick="window.location.href='ValidateUserServlet'" style="background-color: #607d8b; border-color: #607d8b;"><h5 id="buttonText">Volver al inicio</h5></button>
	</div>
</body>
</html>
