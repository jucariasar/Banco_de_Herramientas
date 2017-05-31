
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

<h3>Enlaces</h3>

	<ul>
		<li><a href="http://localhost:8084/aplicacion/Registro?accion=1">Registrar Regional</a></li>
		<li><a href="http://localhost:8084/aplicacion/Registro?accion=2">Registrar Centro</a></li>
		<li><a href="http://localhost:8084/aplicacion/Registro?accion=3">Registrar Area</a></li>
		<li><a href="http://localhost:8084/aplicacion/Registro?accion=4">Registrar Persona</a></li>
		<li><a href="http://localhost:8084/aplicacion/Registro?accion=5">Registrar Elemento</a></li>
	</ul>

<%@ include file="/footer.jsp" %>
