<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Ingreso al Sistema</title>
        <link rel="stylesheet" type="text/css" href="css/estiloslogin.css" />
    </head>
    <body>
        <form method="POST" action="./Login">
            <label for="">Iniciar Sesión</label><br>
            
            <input type="text" name="documento" placeholder="N° de Documento" required><br/>
            <input type="password" name="passwd" placeholder="Contraseña" required><br/>
            
            <input type="submit" value="Ingresar"> <br>
            <a href="" >Olvide mi contraseña</a>
            
        </form>
    </body>
</html>