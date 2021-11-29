USE tienda;

#a) Lista el nombre de todos los productos que hay en la tabla producto. 
SELECT nombre FROM producto;

#b) Lista los nombres y los precios de todos los productos de la tabla producto.
SELECT nombre, precio FROM producto;

#c) Listar aquellos productos que su precio esté entre 120 y 202.
SELECT * FROM producto WHERE precio >= 120 AND precio <= 202;

#d) Buscar y listar todos los Portátiles de la tabla producto. 
SELECT * FROM producto WHERE nombre LIKE '%Portátil%';

#e) Listar el nombre y el precio del producto más barato. 
SELECT nombre, precio FROM producto WHERE precio = (SELECT MIN(precio) FROM producto);

# f) Ingresar un producto a la base de datos
INSERT INTO producto (nombre, precio, codigo_fabricante) VALUES ('prueba', 4.20, 5);
DELETE FROM producto WHERE nombre LIKE 'prueba';

# g) Ingresar un fabricante a la base de datos
INSERT INTO fabricante (nombre) VALUES ('prueba');
DELETE FROM fabricante WHERE nombre LIKE 'prueba';

# h) Editar un producto con datos a elección
UPDATE producto 
	SET nombre = 'prueba2',
    precio = 6.69,
    codigo_fabricante = 4
    WHERE codigo = 12;