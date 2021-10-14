package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Producto;

/**
 *
 * @author Matias Luca Soto
 */
public final class ProductoDAO extends DAO {

    /**
     * a) Lista el nombre de todos los productos que hay en la tabla producto.
     *
     * @return ArrayList de tipo Producto con todos los registros de la tabla 'Producto'
     * @throws Exception
     */
    public Collection<Producto> listarProductos() throws Exception {
        try {

            //Preparo la sentencia query nativa
            String sql = "SELECT * FROM producto";

            //Ejecuto el método de consulta que guardará el resultado en el Objeto ResultSet
            consultarBase(sql);

            //Preparo mis Objetos auxiliares para armar a los productos y su lista
            Producto producto;
            Collection<Producto> productos = new ArrayList();

            //Mientras haya un siguiente registro en 'resultado', entro al bucle y cargo los atributos en mi Objeto auxiliar y lo guardo
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }

            //Devuelvo la lista con los productos cargados
            return productos;

        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error listando a todos los productos :(.");
        } finally {
            desconectarBase();
        }
    }

    // b) Lista los nombres y los precios de todos los productos de la tabla producto.
//    public Collection<String> listarNombresPrecios() throws Exception {
//        try {
//
//            String sql = "SELECT nombre, precio FROM producto";
//
//            consultarBase(sql);
//
//            Collection<String> nombres_precios = new ArrayList();
//            while (resultado.next()) {
//                nombres_precios.add(resultado.getString(1) + " - $" + resultado.getString(2));
//            }
//            return nombres_precios;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception("Error listando los nombres y precios de los productos.");
//        } finally {
//            desconectarBase();
//        }
//    }
    /**
     * *
     * c) Listar aquellos productos que su precio esté entre 120 y 202.
     *
     * @return La lista con todos los productos con precios entre 120 y 202 cargados
     * @throws Exception
     */
    public Collection<Producto> listarProductosEntre120y202() throws Exception {
        try {

            //Sentencia query nativa
            String sql = "SELECT * FROM producto WHERE precio >= 120 AND precio <= 202";

            //Método de consulta y guardado de la tabla en el Objeto ResultSet
            consultarBase(sql);

            //Preparo mis Objetos y mi ArrayList auxiliares
            Producto producto;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }

            //Devuelvo la lista
            return productos;

        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error listando a todos los productos entre $120 y $202");
        } finally {
            desconectarBase();
        }
    }

    /**
     * d) Buscar y listar todos los Portátiles de la tabla producto.
     *
     * @return ArrayList con todos los productos que tengan la palabra clave 'Portatil' en la columna nombre
     * @throws Exception
     */
    public Collection<Producto> listarPortatiles() throws Exception {
        try {

            String sql = "SELECT * FROM producto WHERE nombre LIKE '%Portátil%'";

            consultarBase(sql);

            Producto producto;
            Collection<Producto> productos = new ArrayList();
            while (resultado.next()) {
                producto = new Producto();
                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
                productos.add(producto);
            }
            return productos;

        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error listando a todos los portatiles");
        } finally {
            desconectarBase();
        }
    }

    /**
     * e) Listar el nombre y el precio del producto más barato.
     *
     * @return Un String con el nombre y precio del producto con el precio mínimo encontrado en la tabla 'producto'
     * @throws Exception
     */
    public String productoMasBarato() throws Exception {
        try {

            String sql = "SELECT nombre, precio FROM producto WHERE precio = (SELECT MIN(precio) FROM producto)";

            consultarBase(sql);

            String productoMasBarato = null;
            
            /*Aunque sea un solo registro el que se guarde en ResultSet, es necesario igualmente aplicar el while con
            el método next() para poder mover el puntero hacia el registro uno, ya que este comienza antes del primer registro.*/
            while (resultado.next()) {
                productoMasBarato = resultado.getString(1) + " - $" + resultado.getDouble(2);
            }
            return productoMasBarato;

        } catch (Exception e) {
            //e.printStackTrace();
            throw new Exception("Error listando al producto más barato.");
        } finally {
            desconectarBase();
        }
    }

    /**
     * f) Ingresar un producto a la base de datos
     * @param p Producto con sus atributos inicializados listo para ser ingresado en la tabla 'producto' de la base de datos.
     * @throws Exception 
     */
    public void ingresarProducto(Producto p) throws Exception {
        try {

            //Verificación de que no haya llegado el Objeto nulo
            if (p == null) {
                throw new Exception("Debe indicar un producto completo.");
            }

            //Armado de la sentencia nativa query con los atributos del Objeto recibido como parámetro
            String sql = "INSERT INTO producto (nombre, precio, codigo_fabricante) "
                    + "VALUES ('" + p.getNombre() + "', " + p.getPrecio() + ", " + p.getCodigoFabricante() + ")";

            //Se ejecuta el método para actualizar la base de datos, la cual no devuelve ninguna tabla
            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    /**
     * h) Editar un producto con datos a elección
     * @param p Producto con sus atributos ya actualizados a modificar, excepto por el id
     * @throws Exception 
     */
    public void modificarProductoSegunId(Producto p) throws Exception {
        try {

            if (p == null) {
                throw new Exception("Debe indicar el Producto a modificar.");
            }

            /* Armado de la sentencia nativa query para actualizar los valores del Objeto que coincida con el
            id del Objeto pasado como parámetro */
            String sql = "UPDATE producto "
                    + "SET nombre = '" + p.getNombre() + "', "
                    + "precio = " + p.getPrecio() + ", "
                    + "codigo_fabricante = " + p.getCodigoFabricante() + " "
                    + "WHERE codigo = " + p.getCodigo();

            insertarModificarEliminar(sql);

        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

}
