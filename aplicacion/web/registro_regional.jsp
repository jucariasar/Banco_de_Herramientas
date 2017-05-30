<%-- 
    Document   : registro_regional
    Created on : 29-may-2017, 16:05:05
    Author     : camilo
--%>

<%@ include file="header.jsp" %>
        <h1>Registro de Regional</h1>
        <form action="./Registro" method="POST">
		<br><br><br>
		<b>Código de la Regional:</b><input type="number" name="codigo"><br><br>
		<b>Nombre del Departamento:</b><input type="text" name="nombre"><br><br>	
		<input type="submit" value="Insertar Regional" name="r_regional">
	</form>

<%@ include file="footer.jsp" %>

