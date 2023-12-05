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
  
    <title>Genesis Gym Login</title>


    <link href="Login.css" rel="stylesheet">
    
    <% String pFound = (String) request.getAttribute("pFound");
    if (pFound==null){
    	pFound="true";
    }%>
    
  </head>

  <body>
    <form action="ValidateUserServlet" method="post">
      <img src="images/Logo.png">
      <div id="inputContainer">
	  <% if (pFound=="false"){%>
      	<label style="color:red;">El usuario no fue encontrado o su contraseña es incorrecta</label>
      <%}%>
      	<input id="dni" name="dni" type="text" placeholder="DNI" required autofocus>
      	<input name="password" type="password"  placeholder="Contraseña" required>
      </div>
      <div id="buttonContainer">
      	<button type="submit">Ingresar</button>
      </div>
    </form>
    <div id="pContainer">
      	<p>¿Todavía no tienes una cuenta? <a href="SignUp.jsp">Regístrate</a></p>
     </div>
  </body>
</html>