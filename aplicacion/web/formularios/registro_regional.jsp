
    
<%@ include file="/header.jsp" %>

        <h1>Registro de Regional</h1>
        <form method="POST" action="./Registro">
		<br><br><br>
		<b>Código de la Regional:</b><input type="number" name="codigo"><br><br>
		<b>Nombre del Departamento:</b><input type="text" name="nombre"><br><br>	
		<input type="submit" value="Insertar Regional" name="boton_registro">
	</form>

<%@ include file="/footer.jsp" %>