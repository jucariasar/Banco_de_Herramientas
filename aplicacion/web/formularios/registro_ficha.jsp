

<%@ include file="/header.jsp" %>
<h3>Registro de Ficha</h3>
<c:if test="${not empty prma}">
    <form method="POST" action="./Registro">
        <br><br><br>
        <b>Numero de Ficha:</b><input type="number" name="numero" required><br><br>
        
        <b>Programa al que Pertenece:</b>
        <select name="codigo_programa">
            <option type="number" disabled>Seleccione un Programa</option>
            <c:forEach items="${prma}" var="pr">
                <option type="number" value=${pr.getCodigo()}>${pr.getNombre()} - Versión(${pr.getVersion()})</option>
            </c:forEach>
        </select><br><br>                    


        <input type="submit" value="Insertar Ficha" name="boton_registro">

    </form>
</c:if>
<c:if test="${empty prma}">
    <h3>No hay Programas Registrados</h3>
    <p>Deben Existir Programas Registrados en la Base de Datos para Poder Asociar las Fichas</p>
</c:if>

<%@ include file="/footer.jsp" %>
