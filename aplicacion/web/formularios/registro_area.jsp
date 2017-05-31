
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
