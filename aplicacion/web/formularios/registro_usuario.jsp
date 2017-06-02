<%@ include file="/header.jsp" %>

<h1>Registro de Usuario</h1>
<form method="POST" action="./Registro">
    <br><br><br>
    <b>Numero de Documento:</b><input type="number" name="documento" required><br><br>
    <b>Nombres:</b><input type="text" name="nombres" required><br><br>
    <b>Apellidos:</b><input type="text" name="apellidos" required><br><br>
    <b>Telefono 1:</b><input type="text" name="telefono1" required><br><br>
    <b>Telefono 2:</b><input type="text" name="telefono2"><br><br>
    <b>Correo Electronico:</b><input type="email" name="correo" required><br><br>
    <b>Contraseña:</b><input type="password" name="passwd" required><br><br>
    <b>Centro al que Pertenece:</b><br><br>

    <input type="submit" value="Insertar Usuario" name="boton_registro">
</form>

<%@ include file="/footer.jsp" %>
