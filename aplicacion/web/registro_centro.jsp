<%-- 
    Document   : registro_centro
    Created on : 29-may-2017, 16:05:23
    Author     : camilo
--%>

<%@ include file="header.jsp" %>
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

<%@ include file="footer.jsp" %>
