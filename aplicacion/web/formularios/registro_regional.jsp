
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Registro de Regional</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="../css/estilos.css">
        <script src="../bootstrap/js/jquery.js"></script>
        <script src="../css/header.js"></script>
    </head>
<%@ include file="/header.jsp" %>

        <h1>Registro de Regional</h1>
        <form method="POST" action="./Registro">
		<br><br><br>
		<b>Código de la Regional:</b><input type="number" name="codigo"><br><br>
		<b>Nombre del Departamento:</b><input type="text" name="nombre"><br><br>	
		<input type="submit" value="Insertar Regional">
	</form>

<%@ include file="/footer.jsp" %>