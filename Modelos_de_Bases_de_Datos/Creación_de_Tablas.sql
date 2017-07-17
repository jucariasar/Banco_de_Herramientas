/*Creación de la base de datos banco_herramientas*/
CREATE DATABASE banco_herramientas;

/*Se crea la relación regional con todos sus atributos*/
CREATE TABLE regional(
codigo INT PRIMARY KEY,
nombre VARCHAR(30) UNIQUE NOT NULL
);


/*Se crea la relación centro con todos sus atributos y claves foraneas*/
CREATE TABLE centro(
codigo INT PRIMARY KEY,
nombre VARCHAR(60) NOT NULL,
codigo_regional INT NOT NULL,
FOREIGN KEY(codigo_regional) REFERENCES regional(codigo)
);


/*Se crea la relación area con todos sus atributos y claves foraneas*/
CREATE TABLE area(
nombre VARCHAR(60),
codigo_centro INT,
PRIMARY KEY(nombre, codigo_centro),
FOREIGN KEY(codigo_centro) REFERENCES centro(codigo)
);


/*Se crea la relación programa con todos los atribudos descritos en el modelo E-R*/
CREATE TABLE programa(
codigo INT,
version INT,
nombre VARCHAR(100) NOT NULL,
PRIMARY KEY(codigo, version)
);


/*Se crea la relación ficha tal cual se especifica en el modelo relacional*/
CREATE TABLE ficha(
codigo BIGINT UNSIGNED PRIMARY KEY,
nombre_area VARCHAR(60) NOT NULL,
codigo_centro INT NOT NULL,
codigo_programa INT NOT NULL,
version_programa INT NOT NULL,
FOREIGN KEY(nombre_area, codigo_centro) REFERENCES area(nombre, codigo_centro),
FOREIGN KEY(codigo_programa, version_programa) REFERENCES programa(codigo, version)
);


/*Se crea la relación persona tal cual se especifica en el modelo relacional*/
CREATE TABLE usuario(
carne BIGINT UNSIGNED PRIMARY KEY,
nombres VARCHAR(60) NOT NULL,
apellidos VARCHAR(60) NOT NULL,
telefono_1 VARCHAR(15) NOT NULL,
telefono_2 VARCHAR(15),
email VARCHAR(80) UNIQUE NOT NULL,
passwd CHAR(40) BINARY NOT NULL,
tipo VARCHAR(30) NOT NULL
);

/*Relación que almacena las fotos de los usuarios*/
CREATE TABLE foto_usuario(
id_usuario BIGINT UNSIGNED PRIMARY KEY,
nombre VARCHAR(60) NOT NULL,
contenido MEDIUMBLOB NOT NULL,
tipo VARCHAR(50) NOT NULL,
FOREIGN KEY(id_usuario) REFERENCES usuario(carne)
);


/*Relación para guardar los estados de los aprendices*/
CREATE TABLE estado_aprendiz(
descripcion VARCHAR(20) PRIMARY KEY
);


/*Se creal la relación aprendiz segun lo especificado en el modelo relacional*/
CREATE TABLE aprendiz(
carne BIGINT UNSIGNED PRIMARY KEY,
codigo_ficha BIGINT UNSIGNED NOT NULL,
estado VARCHAR(20) NOT NULL,
FOREIGN KEY(carne) REFERENCES usuario(carne),
FOREIGN KEY(codigo_ficha) REFERENCES ficha(codigo),
FOREIGN KEY(estado) REFERENCES estado_aprendiz(descripcion)
);


/*Relación para almacenar los tipos de empleado en la institución*/
CREATE TABLE tipo_labor(
descripcion VARCHAR(30) PRIMARY KEY
);


/*Se crea la relación empleado tal cual como se especifica en el modelo relacional*/
CREATE TABLE empleado(
supervisor BIGINT UNSIGNED,
carne BIGINT UNSIGNED PRIMARY KEY,
tipo_labor VARCHAR(30) NOT NULL,
tipo VARCHAR(20) NOT NULL,
FOREIGN KEY(supervisor) REFERENCES empleado(carne),
FOREIGN KEY(carne) REFERENCES usuario(carne),
FOREIGN KEY(tipo_labor) REFERENCES tipo_labor(descripcion)
);


/*Se crea la relación para empleados de planta*/
CREATE TABLE planta(
grado INT,
fecha_vinculacion DATE,
carne BIGINT UNSIGNED PRIMARY KEY,
FOREIGN KEY(carne) REFERENCES usuario(carne)
);


/*Se crea la relación para empleados contratistas*/
CREATE TABLE contratista(
numero_contrato INT,
fecha_inicio DATE,
fecha_fin DATE,
carne BIGINT UNSIGNED PRIMARY KEY,
FOREIGN KEY(carne) REFERENCES usuario(carne)
);


/*Relación que guarda los roles*/
CREATE TABLE roll(
descripcion VARCHAR(30) PRIMARY KEY
);


/*Relación de intersección para perfiles de empleados*/
CREATE TABLE perfil(
carne_empleado BIGINT UNSIGNED,
roll VARCHAR(30),
PRIMARY KEY(carne_empleado, roll),
FOREIGN KEY(carne_empleado) REFERENCES empleado(carne),
FOREIGN KEY(roll) REFERENCES roll(descripcion)
);


/*Categoria de los elementos*/
CREATE TABLE categoria(
descripcion VARCHAR(30) PRIMARY KEY
);


/*Creación de relacion elemento*/
CREATE TABLE elemento(
codigo BIGINT UNSIGNED,
nombre VARCHAR(100) NOT NULL,
ubicacion VARCHAR(100) NOT NULL,
valor_unidad BIGINT UNSIGNED NOT NULL,
observacion VARCHAR(100),
nombre_area VARCHAR(60),
codigo_centro INT,
categoria VARCHAR(30),
tipo VARCHAR(30) NOT NULL,
PRIMARY KEY(codigo, nombre_area, codigo_centro),
FOREIGN KEY(nombre_area, codigo_centro) REFERENCES area(nombre, codigo_centro),
FOREIGN KEY(categoria) REFERENCES categoria(descripcion)
);


/*Relación para almacenar las fotos de los elementos*/
CREATE TABLE foto_elemento(
id BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
nombre VARCHAR(60) NOT NULL,
contenido MEDIUMBLOB NOT NULL,
tipo VARCHAR(50) NOT NULL,
PRIMARY KEY(id, nombre_area, codigo_centro),
FOREIGN KEY(id, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro)
);


/*Relación que almacena el estado de los elementos devolutivos*/
CREATE TABLE estado_devolutivo(
descripcion VARCHAR(30) PRIMARY KEY
);


/*Creación de relación elemento devolutivo*/
CREATE TABLE devolutivo(
codigo BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
estado VARCHAR(20) NOT NULL,
PRIMARY KEY(codigo, nombre_area, codigo_centro),
FOREIGN KEY(codigo, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro),
FOREIGN KEY(estado) REFERENCES estado_devolutivo(descripcion)
);


/**/
CREATE TABLE consumo(
cantidad_total INT NOT NULL,
cantidad_prestada INT NOT NULL,
codigo BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
PRIMARY KEY(codigo, nombre_area, codigo_centro),
FOREIGN KEY(codigo, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro)
);


/*Revisar esta realación y consultar si una clave foranea tiene que apuntar a todos las claves primarias
de la otra relación o se puede hacer como en esta que codigo_elemento*/
CREATE TABLE cuentadante(
codigo_elemento BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
carne_empleado BIGINT UNSIGNED,
PRIMARY KEY(codigo_elemento, nombre_area, codigo_centro, carne_empleado),
FOREIGN KEY(codigo_elemento, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro),
FOREIGN KEY(carne_empleado) REFERENCES empleado(carne)
);


/*Relacion reserva*/
CREATE TABLE reserva(
fecha_reserva DATE NOT NULL,
fecha_vencimiento DATE NOT NULL,
cantidad_reservada INT NOT NULL,
codigo_elemento BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
carne_empleado BIGINT UNSIGNED,
observacion VARCHAR(100),
PRIMARY KEY(codigo_elemento, nombre_area, codigo_centro, carne_empleado),
FOREIGN KEY(codigo_elemento, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro),
FOREIGN KEY(carne_empleado) REFERENCES empleado(carne)
);


/*Relación prestamos*/
CREATE TABLE prestamo(
fecha_prestamo DATE NOT NULL,
fecha_vencimiento DATE NOT NULL,
cantidad_prestada INT NOT NULL,
codigo_elemento BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
carne_usuario BIGINT UNSIGNED,
observacion VARCHAR(100),
PRIMARY KEY(codigo_elemento, nombre_area, codigo_centro, carne_usuario),
FOREIGN KEY(codigo_elemento, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro),
FOREIGN KEY(carne_usuario) REFERENCES empleado(carne)
);


/*Relación mora*/
CREATE TABLE mora(
fecha_prestamo DATE NOT NULL,
cantidad_prestada INT NOT NULL,
codigo_elemento BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
carne_usuario BIGINT UNSIGNED,
observacion VARCHAR(100),
PRIMARY KEY(codigo_elemento, nombre_area, codigo_centro, carne_usuario),
FOREIGN KEY(codigo_elemento, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro),
FOREIGN KEY(carne_usuario) REFERENCES empleado(carne)
);