

<%@ include file="/header.jsp" %>
<h1>Registro de Programas</h1>
<form method="POST" action="./Registro">
    <br><br><br>
    <b>Código del Programa:</b><input type="number" name="codigo" required><br><br>
    <b>Versión del Programa:</b><input type="number" name="version" required><br><br>
    <b>Nombre del Programa:</b><input type="text" name="nombre" required><br><br>
    <input type="submit" value="Insertar Programa" name="boton_registro">
</form>
<%@ include file="/footer.jsp" %>