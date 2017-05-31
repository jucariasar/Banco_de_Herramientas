
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

        <h1>Registro de Area</h1>
        <form action="insertarPaseo.php" method="POST">
		<br><br><br>
		<b>Nombre del Área:</b><input type="text" name="nombre"><br><br>
		<b>Centro al que pertenece:</b><input type="text" name="nombre_centro"><br><br>
		<select name="codigo_centro">
                    <option type="number" value="9204">CTMA</option>
                    <option type="number" value="9206">CTGI</option>
                    <option type="number" value="9203">CDHC</option>
		</select><br><br>
		<input type="submit" value="Insertar">
	</form>
        
<%@ include file="/footer.jsp" %>
