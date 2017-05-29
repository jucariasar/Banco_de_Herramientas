/*Creación de la base de datos banco_herramientas*/
CREATE DATABASE banco_herramientas;

/*Se crea la relación regional con todos sus atributos*/
CREATE TABLE regional(
codigo INT PRIMARY KEY,
nombre_departamento VARCHAR(30) UNIQUE NOT NULL
);


/*Se crea la relación centro con todos sus atributos y claves foraneas*/
CREATE TABLE centro(
codigo INT PRIMARY KEY,
nombre VARCHAR(60) NOT NULL,
codigo_regional INT,
FOREIGN KEY(codigo_regional) REFERENCES regional(codigo)
);


/*Se crea la relación area con todos sus atributos y claves foraneas*/
CREATE TABLE area(
nombre VARCHAR(60) NOT NULL,
codigo_centro INT NOT NULL,
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
numero_ficha BIGINT UNSIGNED PRIMARY KEY,
nombre_area VARCHAR(60) NOT NULL,
codigo_centro INT NOT NULL,
codigo_programa INT NOT NULL,
version_programa INT NOT NULL,
FOREIGN KEY(nombre_area, codigo_centro) REFERENCES area(nombre, codigo_centro),
FOREIGN KEY(codigo_programa, version_programa) REFERENCES programa(codigo, version)
);


/*Se crea la relación persona tal cual se especifica en el modelo relacional*/
CREATE TABLE persona(
carne BIGINT UNSIGNED PRIMARY KEY,
nombres VARCHAR(60) NOT NULL,
apellidos VARCHAR(60) NOT NULL,
telefono_1 VARCHAR(15) NOT NULL,
telefono_2 VARCHAR(15),
email VARCHAR(80) UNIQUE NOT NULL,
passwd CHAR(32) BINARY NOT NULL,
nombre_area VARCHAR(60) NOT NULL,
codigo_centro INT NOT NULL,
tipo VARCHAR(30) NOT NULL,
FOREIGN KEY(nombre_area, codigo_centro) REFERENCES area(nombre, codigo_centro)
);


/*Se creal la relación aprendiz segun lo especificado en el modelo relacional*/
CREATE TABLE aprendiz(
estado VARCHAR(20) NOT NULL,
carne BIGINT UNSIGNED PRIMARY KEY,
numero_ficha BIGINT UNSIGNED NOT NULL,
FOREIGN KEY(carne) REFERENCES persona(carne),
FOREIGN KEY(numero_ficha) REFERENCES ficha(numero_ficha)
);


/*Se crea la relación empleado tal cual como se especifica en el modelo relacional*/
CREATE TABLE empleado(
tipo VARCHAR(20) NOT NULL,
supervisor BIGINT UNSIGNED,
carne BIGINT UNSIGNED PRIMARY KEY,
tipo_empleado VARCHAR(20) NOT NULL,
FOREIGN KEY(supervisor) REFERENCES empleado(carne),
FOREIGN KEY(carne) REFERENCES persona(carne)
);


/*Se crea la relación para empleados de planta*/
CREATE TABLE planta(
grado INT,
fecha_vinculacion DATE,
carne BIGINT UNSIGNED PRIMARY KEY,
FOREIGN KEY(carne) REFERENCES persona(carne)
);


/*Se crea la relación para empleados contratistas*/
CREATE TABLE contratista(
numero_contrato INT,
fecha_inicio DATE,
fecha_fin DATE,
carne BIGINT UNSIGNED PRIMARY KEY,
FOREIGN KEY(carne) REFERENCES persona(carne)
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


/*Creación de relacion elemento*/
CREATE TABLE elemento(
codigo BIGINT UNSIGNED,
nombre VARCHAR(100) NOT NULL,
ubicacion VARCHAR(100) NOT NULL,
valor_unidad BIGINT UNSIGNED NOT NULL,
nombre_area VARCHAR(60),
codigo_centro INT,
tipo VARCHAR(30) NOT NULL,
PRIMARY KEY(codigo, nombre_area, codigo_centro),
FOREIGN KEY(nombre_area, codigo_centro) REFERENCES area(nombre, codigo_centro)
);


/*Creación de relación elemento devolutivo*/
CREATE TABLE devolutivo(
estado VARCHAR(20) NOT NULL,
codigo BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
PRIMARY KEY(codigo, nombre_area, codigo_centro),
FOREIGN KEY(codigo, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro)
);


/**/
CREATE TABLE consumo(
cantidad_total INT NOT NULL,
cantidad_disponible INT NOT NULL,
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
carne_empleado BIGINT UNSIGNED,
nombre_area VARCHAR(60),
codigo_centro INT,
PRIMARY KEY(codigo_elemento, carne_empleado, nombre_area, codigo_centro),
FOREIGN KEY(codigo_elemento, nombre_area, codigo_centro) REFERENCES elemento(codigo, nombre_area, codigo_centro),
FOREIGN KEY(carne_empleado) REFERENCES empleado(carne)
);