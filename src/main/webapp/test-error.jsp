<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Forzamos una excepción para probar ErrorPage.jsp
    if (true) {
        throw new RuntimeException("Error de prueba para validar la redirección global.");
    }
%>