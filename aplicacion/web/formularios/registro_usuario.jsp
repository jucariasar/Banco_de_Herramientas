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
    <b>Contrase�a:</b><input type="password" name="passwd" required><br><br>
    <b>Centro al que Pertenece:</b><br><br>

    <input type="submit" value="Insertar Usuario" name="boton_registro">
</form>
<p>GNU/Linux, es el t�rmino empleado para referirse a la combinaci�n del sistema operativo GNU, desarrollado por la FSF, y el n�cleo(kernel) Linux, desarrollado por Linus Torvalds y la Linux Foundation. Su desarrollo es uno de los ejemplos m�s prominentes de software libre; todo su c�digo fuente puede ser utilizado, modificado y redistribuido libremente por cualquiera bajo los t�rminos de la GPL (Licencia P�blica General de GNU) y otra serie de licencias libres.1

A pesar de que "Linux" se denomina en la jerga cotidiana al sistema operativo,2 3 este es en realidad solo el Kernel (n�cleo) del sistema. La verdadera denominaci�n del sistema operativo es "GNU/Linux" debido a que el resto del sistema (la parte fundamental de la interacci�n entre el hardware y el usuario) se maneja con las herramientas del proyecto GNU (www.gnu.org) y con entornos de escritorio (como GNOME), que tambi�n forma parte del proyecto GNU aunque tuvo un origen independiente. Como el Proyecto GNU destaca,4 GNU es una distribuci�n, us�ndose el t�rmino sistema operativo en el sentido empleado en el ecosistema Unix, lo que en cualquier caso significa que Linux es solo una pieza m�s dentro de GNU/Linux. Sin embargo, una parte significativa de la comunidad, as� como muchos medios generales y especializados, prefieren utilizar el t�rmino Linux para referirse a la uni�n de ambos proyectos.

Adem�s, existen distribuciones de Linux que no tienen componentes GNU, por ejemplo Android, y distribuciones GNU que tienen n�cleo distinto a Linux, por ejemplo Debian GNU/Hurd.

Para m�s informaci�n consulte la secci�n "Denominaci�n GNU/Linux" o el art�culo "Controversia por la denominaci�n GNU/Linux".</p>

<%@ include file="/footer.jsp" %>
