

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Directiva taglib de JSP-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Cosas por hacer</title>
</head>
<body>
	<!-- Formulario 1 -->
    <form name="ejemplo1" action="17-jquery-change-demo1.php" method="POST">
        <select name="color1">
            <option value="0">Selecciona una opción</option>
            <option value="azul">Azul</option>
            <option value="rojo">Rojo</option>
        </select>
        <input type="text" name="valor1" size="40" value="Aquí saldrá el valor del select cuando cambie">
    </form>
    
    <!-- Formulario 2 -->
    <form name="ejemplo2" action="17-jquery-change-demo1.php" method="POST">
        <select name="color2" id="ejemplo2">
            <option value="0">Selecciona una opción</option>
            <option value="azul">Azul</option>
            <option value="rojo">Rojo</option>
        </select>
        <input type="text" name="valor2" size="40" id="valor2" value="Aquí saldrá el valor del select cuando cambie">
    </form>
    
    <!-- Formulario 3 -->
    <form name="ejemplo3" action="17-jquery-change-demo1.php" method="POST">
        <select name="color3" class="ejemplo3">
            <option value="0">Selecciona una opción</option>
            <option value="azul">Azul</option>
            <option value="rojo">Rojo</option>
        </select>
        <input type="text" name="valor3" size="40" class="valor3" value="Aquí saldrá el valor del select cuando cambie">
    </form>

	<!--
	<h2>Cosas por hacer</h2>
	<form name="checkListForm">
		<input type="text" name="itemDeLista"/>
	</form>

	<div id="boton">
		¡Agregar!
	</div>
	
	<div class="lista">
		
	</div>-->
	
	<script src="../../bootstrap/js/jquery.js"></script>
	<script src="script.js"></script>
</body>
</html>