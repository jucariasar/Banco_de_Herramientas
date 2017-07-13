

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Directiva taglib de JSP-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba AJAX</title>
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    </head>
    <body>
        <h1>Registro de Usuarios</h1>
        <c:if test="${not empty rgnl}">
            <div class="container">
                <br>
                <form  method="POST" action="./Prueba_Ajax" class="form-horizontal">

                    <div class="form-group">
                        <label for="carne" class="control-label col-md-2">Carné:</label>
                        <div class="col-md-10">
                            <input type="number" class="form-control" id="carne" placeholder="Carné">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="nombres" class="control-label col-md-2">Nombres:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" id="nombres" placeholder="Nombres">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="apellidos" class="control-label col-md-2">Apellidos:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" id="apellidos" placeholder="Apellidos">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="telefono1" class="control-label col-md-2">Telefono 1:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" id="telefono1" placeholder="Telefono 1">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="telefono2" class="control-label col-md-2">Telefono 2:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" id="telefono2" placeholder="Telefono 2">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-md-2">E-mail:</label>
                        <div class="col-md-10">
                            <input type="email" class="form-control" id="email" placeholder="Correo Electrónico">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="regionales" class="control-label col-md-2">Regional:</label>
                        <div class="col-md-10">
                            <select class="form-control" id="regionales">
                                <option type="number" value="">Seleccione una regional</option>
                                <c:forEach items="${rgnl}" var="rg">
                                    <option type="number" value=${rg.getCodigo()}>${rg.getNombre_departamento()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="centros" class="control-label col-md-2">Centro:</label>
                        <div class="col-md-10">
                            <select class="form-control" id="centros">
                                <option type="number" value="">Seleccione un centro de formación</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="areas" class="control-label col-md-2">Area:</label>
                        <div class="col-md-10">
                            <select class="form-control" id="areas">
                                <option type="number" value="">Seleccione un área</option>
                            </select>
                        </div>
                    </div>
                    <!--
                    <div class="form-group">
                        <label for="prueba" class="control-label col-md-2">E-mail:</label>
                        <div id="pruebaPadre" class="col-md-10">
                            <input type="checkbox" id="prueba"/>Prueba
                        </div>
                    </div>-->


                    <div class="form-group">
                        <label for="tuser" class="control-label col-md-2">Tipo de Usuario:</label>
                        <div id="cktuser" class="col-md-10">
                            <div id="product1">
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="1" name="check" id="check1">Aprendiz  
                                </label>

                                <label class="checkbox-inline">
                                    <input type="checkbox" value="2" name="check" id="check2">Empleado
                                </label>
                            </div>
                        </div>
                    </div>

                    <input type="submit" value="Registrar Usuario" name="boton_registro">

                </form>

            </div>
        </c:if>

        <script src="bootstrap/js/jquery.js"></script>
        <script src="js/pruebaajax.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>

    </body>
</html>
