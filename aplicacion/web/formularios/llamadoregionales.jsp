<%-- 
    Document   : llamadoregionales
    Created on : 15-jun-2017, 21:00:10
    Author     : camilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>llamado regionales</title>
<script language="javascript">
	function llamado(){
		ctexto=document.getElementById("tuform").consul;
		top.abajo.location="consul.php?id="+ctexto.value;
	}
	function retorno(valor){
		atexto=document.getElementById("tuform").nombre;
		atexto.value=valor;
	}
</script>
</head>

<body>
<form name="tuform" id="tuform">
    <select name="codigo_regional">
            <c:forEach items="${rgnl}" var="rg">
                <option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
            </c:forEach>       
        </select>
	ID del Alumno: <input type="text" name="consul" id="consul">
	<button type="button" onClick="llamaphp()">Consultar</button><br>
	Nombre del Alumno: <input type="text" name="nombre" id="nombre">
</form>
</body>
</html> 
