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
<p>GNU/Linux, es el término empleado para referirse a la combinación del sistema operativo GNU, desarrollado por la FSF, y el núcleo(kernel) Linux, desarrollado por Linus Torvalds y la Linux Foundation. Su desarrollo es uno de los ejemplos más prominentes de software libre; todo su código fuente puede ser utilizado, modificado y redistribuido libremente por cualquiera bajo los términos de la GPL (Licencia Pública General de GNU) y otra serie de licencias libres.1

A pesar de que "Linux" se denomina en la jerga cotidiana al sistema operativo,2 3 este es en realidad solo el Kernel (núcleo) del sistema. La verdadera denominación del sistema operativo es "GNU/Linux" debido a que el resto del sistema (la parte fundamental de la interacción entre el hardware y el usuario) se maneja con las herramientas del proyecto GNU (www.gnu.org) y con entornos de escritorio (como GNOME), que también forma parte del proyecto GNU aunque tuvo un origen independiente. Como el Proyecto GNU destaca,4 GNU es una distribución, usándose el término sistema operativo en el sentido empleado en el ecosistema Unix, lo que en cualquier caso significa que Linux es solo una pieza más dentro de GNU/Linux. Sin embargo, una parte significativa de la comunidad, así como muchos medios generales y especializados, prefieren utilizar el término Linux para referirse a la unión de ambos proyectos.

Además, existen distribuciones de Linux que no tienen componentes GNU, por ejemplo Android, y distribuciones GNU que tienen núcleo distinto a Linux, por ejemplo Debian GNU/Hurd.

Para más información consulte la sección "Denominación GNU/Linux" o el artículo "Controversia por la denominación GNU/Linux".</p>

<%@ include file="/footer.jsp" %>
