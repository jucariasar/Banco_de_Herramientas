<%@ include file="/header.jsp" %>

<h1>Registro de Usuario</h1>
<c:if test="${not empty rgnl}">
    <form method="POST" action="./Registro">
        <br><br><br>
        <b>Numero de Documento:</b><input type="number" name="documento" required><br><br>
        <b>Nombres:</b><input type="text" name="nombres" required><br><br>
        <b>Apellidos:</b><input type="text" name="apellidos" required><br><br>
        <b>Telefono 1:</b><input type="text" name="telefono1" required><br><br>
        <b>Telefono 2:</b><input type="text" name="telefono2"><br><br>
        <b>Correo Electronico:</b><input type="email" name="correo" required><br><br>
        <b>Contraseña:</b><input type="password" name="passwd" required><br><br>
        <b>Regional a la que Pertenece:</b>
        <select name="codigoR">
            <c:forEach items="${rgnl}" var="rg">
                <option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
            </c:forEach>
        </select><br>
        <c:if test="${not empty cent}">
            <b>Centro al que Pertenece:</b>
            <select name="codigo_centro">
                <option type="number" selected></option>
                <c:forEach items="${cent}" var="ct">
                    <option type="number" value=${ct.getCodigo()}>${ct.getNombre()}</option>
                </c:forEach>
            </select><br><br>
        </c:if>
        

        <input type="submit" value="Insertar Usuario" name="boton_registro">
    </form>
</c:if>
<%@ include file="/footer.jsp" %>
