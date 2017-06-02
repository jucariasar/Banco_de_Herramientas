

<%@ include file="/header.jsp" %>

<h3>Registro de Centro</h3>
<c:if test="${not empty rgnl}">
    <form method="POST" action="./Registro">
        <br><br><br>
        <b>Código del Centro:</b><input type="number" name="codigo" required><br><br>
        <b>Nombre del Centro:</b><input type="text" name="nombre" required><br><br>


        <b>Seleccione una Regional:</b>
        <select name="codigo_regional">       
            <c:forEach items="${rgnl}" var="rg">
                <option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
            </c:forEach>
        </select><br><br>                    


        <input type="submit" value="Insertar Centro" name="boton_registro">
        
    </form>
</c:if>
<c:if test="${empty rgnl}">
    <h3>No hay Rhegionales Registradas</h3>
    <p>Deben Existir Regionales Registradas en la Base de Datos para Poder Asociar Centros</p>
</c:if>

<%@ include file="/footer.jsp" %>
