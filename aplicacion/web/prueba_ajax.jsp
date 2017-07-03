

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Directiva taglib de JSP-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba AJAX y JSON</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <h1>Registro de Usuarios</h1>
        <c:if test="${not empty rgnl}">
            <div class="container">
                <form method="POST" action="./Prueba_Ajax">
                    <div class="form-group">
                        <label for="carne">Carné</label>
                        <input class="form-control" id="carne" type="number" placeholder="Carné:">
                    </div>

                    <div class="form-group">
                        <label for="nombres">Nombres</label>
                        <input class="form-control" id="nombres" type="text" placeholder="Nombre:">
                    </div>

                    <div class="form-group">
                        <label for="apellidos">Apellidos</label>
                        <input class="form-control" id="apellidos" type="text" placeholder="Apellidos:">
                    </div>

                    <div class="form-group">
                        <label for="telefono1">Telefono 1</label>
                        <input class="form-control" id="telefono1" type="text" placeholder="Telefono 1:">
                    </div>

                    <div class="form-group">
                        <label for="telefono2">Telefono 2</2></label>
                        <input class="form-control" id="telefono2" type="text" placeholder="Telefono 2:">
                    </div>

                    <div class="form-group">
                        <label for="email">Correo Electrónico</label>
                        <input class="form-control" id="email" type="email" placeholder="Correo Electrónico:">
                    </div>

                    <div class="form-group">
                        <label for="regionales">Regional</label>
                        <select class="form-control" id="regionales">
                            <option type="number" value="">Seleccione una regional</option>
                            <c:forEach items="${rgnl}" var="rg">
                                <option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="centros">Centro</label>
                        <select class="form-control" id="centros">
                            <option type="number" id="encabezado1" value="">Seleccione un centro de formación</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="areas">Area</label>
                        <select class="form-control" id="areas">
                            <option type="number" value="">Seleccione un área</option>
                            <c:forEach items="${rgnl}" var="rg">
                                <option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="valorselect">Valor del select</label>
                        <input class="form-control" id="valorselect" type="text">
                    </div>

                    <input type="submit" value="Registrar Usuario" name="boton_registro">

                </form>
            </div>
        </c:if>

        <script src="bootstrap/js/jquery.js"></script>
        <script src="css/pruebaajax.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>
