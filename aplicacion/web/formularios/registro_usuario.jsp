
<%@ include file="/header.jsp" %>

<h1>Registro de Usuario</h1>
<c:if test="${not empty rgnl}">
<form name="formR" id="formR" methor="POST" acction="./Registro">
	<form name="formR1" id="formR1" method="GET" action="./Registro2">
		<br><br>
		<b>Numero de Documento:</b><input type="number" name="documento" required><br><br>
		<b>Nombres:</b><input type="text" name="nombres" required><br><br>
		<b>Apellidos:</b><input type="text" name="apellidos" required><br><br>
		<b>Telefono 1:</b><input type="text" name="telefono1" required><br><br>
		<b>Telefono 2:</b><input type="text" name="telefono2"><br><br>
		<b>Correo Electronico:</b><input type="email" name="correo" required><br><br>
		<b>Contraseña:</b><input type="password" name="passwd" required><br><br>
		<b>Regional a la que Pertenece:</b>
		<select name="codigo_regional" id="codigo_regional">
			<c:forEach items="${rgnl}" var="rg">
				<option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
			</c:forEach>
		</select>
		<button type="submit" class="btn btn-sm btn-primary" id="consutar-1">Consultar</button>
	</form>   

	<c:if test="${not empty cent}">
		<b>Centros asociados:</b>
		<select name="codigo_centro">
			<c:forEach items="${cent}" var="ct">
				<option type="number" value=${ct.getCodigo()}>${ct.getNombre()} (${ct.getCodigo_regional()})</option>
			</c:forEach>
		</select>        
	</c:if>
</form>
</c:if>

<%@ include file="/footer.jsp" %>