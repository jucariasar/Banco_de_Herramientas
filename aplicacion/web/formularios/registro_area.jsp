
<%@ include file="/header.jsp" %>

<h1>Registro de Area</h1>
<c:if test="${not empty cent}">
    <form method="POST" action="./Registro">
        <br><br><br>
        <b>Nombre del Área:</b><input type="text" name="nombre" required><br><br>
        <b>Seleccione un Centro:</b>
        <select name="codigo_centro">
            <option type="number" selected></option>
            <c:forEach items="${cent}" var="ct">
                <option type="number" value=${ct.getCodigo()}>${ct.getNombre()} (${ct.getNombreRegional()})</option>
            </c:forEach>
        </select><br><br> 
        <input type="submit" value="Insertar Area" name="boton_registro">
    </form>
</c:if>
<c:if test="${empty cent}">
    <h3>No hay Centros Registrados</h3>
    <p>Deben Existir Centros Registrados en la Base de Datos para Poder Asociar Areas</p>
</c:if>
<%@ include file="/footer.jsp" %>
