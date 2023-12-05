<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@1/css/pico.min.css">
  
    <title>Genesis Gym Login</title>


    <link href="SignUp.css" rel="stylesheet">
    
    <% String clientFound = (String) request.getAttribute("clientFound");
    String teacherFound = (String) request.getAttribute("teacherFound");
    String clientHasNoTeacher = (String) request.getAttribute("clientHasNoTeacher");
    
    if (clientFound==null){
    	clientFound="false";
    }
    if (teacherFound==null){
    	teacherFound="true";
    }
    if (clientHasNoTeacher==null){
    	clientHasNoTeacher="false";
    }%>
  </head>

  <body>
  	<div class="logoContainer">
  		<img id="logo" src="images/Logo.png">
  	</div>
    <div class="pContainer">
      	<h4>Ingrese sus datos personales</h4>
     </div>
    <form action="CreateUserServlet" method="post">
      <div id="inputContainer">
      	<% if (clientFound=="true"){%>
      		<label style="color:red;">El DNI ingresado ya existe en el sistema</label>
      	<%}%>
      	<input name="dni" type="number" placeholder="DNI"
      	<% if (clientFound=="true"){%> 
      		aria-invalid="true"
      	<%}%>
      	required autofocus>
      	<input  name="name" type="text" placeholder="Nombre" required>
      	<input name="lastname" type="text" placeholder="Apellido" required>
      	<label style="color:#dd8f26;">Fecha de nacimiento</label>
      	<input name="birthdate" type="date" placeholder="DNI" required>
      	<input name="phoneNumber" type="text" placeholder="Número de teléfono" required>
      	<input name="password" type="password"  placeholder="Contraseña" required>
      	
      	<% if (teacherFound=="false"){%>
      		<label style="color:red;">El DNI del profesor no es válido</label>
      	<%}%>
      	<% if (clientHasNoTeacher=="true"){%>
      		<label style="color:red;">Los clientes deben tener un profesor</label>
      	<%}%>
      	<input name="teacherDni" type="text" placeholder="DNI del profesor (si tuviese)"
      	<% if (teacherFound=="false" || clientHasNoTeacher=="true"){%> 
      		aria-invalid="true"
      	<%}%> >
      	
      	<fieldset>
      	<legend style="color:#dd8f26;">Tipo de usuario</legend>
      	<div class="fieldsetDiv">
			<input type="radio" id="client" name="type" value="client" checked>
			<label for="client">Cliente independiente</label>
      	</div>
      	<div class="fieldsetDiv">
      		<input type="radio" id="teacher" name="type" value="teacher">
      		<label for="teacher">Profesor</label>
      	</div>
      	</fieldset>
      	
      </div>
      <div id="buttonContainer">
      	<button type="submit">Continuar</button>
      </div>
    </form>
  </body>
</html>