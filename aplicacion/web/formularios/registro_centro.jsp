
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

<h1>Registro de Centro</h1>
        <form action="" method="POST">
		<br><br><br>
		<b>Código del Centro:</b><input type="number" name="codigo"><br><br>
		<b>Nombre del Centro:</b><input type="text" name="nombre"><br><br>
                <b>Seleccione una Regional:</b>
		<select name="codigo_regional">
                    <option type="number" value="5">Antioquia</option>
                    <option type="number" value="17">Caldas</option>
                    <option type="number" value="68">Santandes</option>
		</select><br><br>	
		<input type="submit" value="Insertar">
	</form>

<%@ include file="/footer.jsp" %>
