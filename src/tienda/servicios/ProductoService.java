package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

/**
 *
 * @author Matias Luca Soto
 */
public final class ProductoService {

    //ATRIBUTO - OBJETO DAO - Scanner - Fabricante Service
    private final ProductoDAO dao;
    private final Scanner sc;
    private final FabricanteService fabricanteService;

    //CONSTRUCTOR con sus atributos inicializados
    public ProductoService() {
        this.dao = new ProductoDAO();
        this.sc = new Scanner(System.in).useDelimiter("\n");
        this.fabricanteService = new FabricanteService();
    }

    //MÉTODOS
    /**
     * a) Lista el nombre de todos los productos que hay en la tabla producto.
     *
     */
    public void listarNombres() {

        try {

            //Recibo el ArrayList que me devuelve listarProductos() de la entidadDAO
            Collection<Producto> productos = dao.listarProductos();

            //Imprimo el código y nombre de todos los productos en el ArrayList 
            System.out.println("\nNOMBRES DE TODOS LOS PRODUCTOS:");
            for (Producto producto : productos) {
                System.out.println(producto.getCodigo() + "-> " + producto.getNombre());
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al querer mostrar el nombre de los productos :/");
            System.out.println(e.getMessage());
        }

    }

    /**
     * // b) Lista los nombres y los precios de todos los productos de la tabla producto.
     *
     */
    public void listarNombresPrecios() {

        try {

            //Igual que en el método anterior, solo que muestro el precio también
            Collection<Producto> productos = dao.listarProductos();

            //Output del nombre y precio de los productos
            System.out.println("\nNOMBRES Y PRECIOS DE TODOS LOS PRODUCTOS:");
            for (Producto producto : productos) {
                System.out.println("-> " + producto.getNombre() + " - $" + producto.getPrecio());
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al querer mostrar el nombre y precio de los productos >:/");
            System.out.println(e.getMessage());
        }

    }

    /**
     * c) Listar aquellos productos que su precio esté entre 120 y 202.
     */
    public void listarProductosEntre120y202() {
        try {

            //Guardo el ArrayList que me devuelve el método de la entidad DAO
            Collection<Producto> productos = dao.listarProductosEntre120y202();

            //Output de los productos dentro del ArrayList
            System.out.println("\nPRODUCTOS ENTRE $120 Y $202:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al querer mostrar los productos entre $120 y $202"
                    + "\nAcá usamos el método devolverFabricante y puede que la haya cagado");
            System.out.println(e.getMessage());
        }
    }

    /**
     * d) Buscar y listar todos los Portátiles de la tabla producto.
     */
    public void listarPortatiles() {
        try {

            //Guardo el ArrayList que me devuelve el método de la entidad DAO
            Collection<Producto> productos = dao.listarPortatiles();

            //Output de los productos en el Collection
            System.out.println("\nPORTÁTILES EN STOCK:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al querer mostrar los portátiles ¬¬"
                    + "\nAcá usamos el método devolverFabricante y puede que la haya cagado c:");
            System.out.println(e.getMessage());
        }
    }

    /**
     * e) Listar el nombre y el precio del producto más barato.
     */
    public void productoMasBarato() {
        try {

            //Imprimo directamente el String devuelvo por el método de la entidad DAO
            System.out.println("\nPRODUCTO MÁS BARATO ENCONTRADO..."
                    + "\n" + dao.productoMasBarato());

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al querer mostrar el producto más barato");
            System.out.println(e.getMessage());
        }
    }

    // f) Ingresar un producto a la base de datos
    public void ingresarProducto() {
        try {

            //Preparo las variables que funcionaran para ingresar los atributos del nuevo Producto
            String nombre;
            double precio;
            int codigoFabricante;

            //Input de los atributos
            System.out.println("\nA continuación, ingrese los datos de su producto...");
            do {
                System.out.println("Ingrese el nombre:");
                nombre = sc.next();
            } while (nombre.trim().isEmpty());

            do {
                System.out.println("Ingrese el precio");
                precio = sc.nextDouble();
            } while (precio < 0);

            /* Utilizo el método cantidadFabricantes() de FabricanteService para obtener la cantidad
            de Fabricantes en la base de datos y así elegir una opción entre ellos.
            Si bien no es lo más correcto ya que puede dar errores si la PM no es un número que continue
            la enumeración normal, esta aplicacion no elimina registros así que no deberia dar errores. */
            do {
                fabricanteService.imprimirFabricantes();
                System.out.println("\nIngrese el código de fabricante de su producto:");
                codigoFabricante = sc.nextInt();
            } while (codigoFabricante < 1 || codigoFabricante > fabricanteService.cantidadFabricantes());

            //Armo el Producto nuevo a registrar
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setCodigoFabricante(codigoFabricante);

            //Envio el Producto a la entidad DAO
            dao.ingresarProducto(nuevoProducto);

            System.out.println("¡Producto ingresado con éxito!");

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al intentar ingresar un nuevo Producto :/");
            System.out.println(e.getMessage());
        }
    }

    /**
     * h) Editar un producto con datos a elección
     */
    public void modificarProductoPorId() {

        try {

            //Recibo el ArrayList con todos mis Productos listados
            Collection<Producto> productos = dao.listarProductos();

            //Output de todos los productos para seleccionar cual quiero modificar
            System.out.println("A continuación, elija el producto a modificar ingresando su código:");
            System.out.println("\nListado de productos en stock:");
            for (Producto producto : productos) {
                System.out.println(producto);
            }

            //Input del código del producto a modificar
            /* Caso similar al método anterior: el do-while solo chequea que el codigo del fabricante (FK)
            esté entre la cantidad de elementos listados de la tabla Productos.
            Esto no es lo más eficiente pero al no poder eliminarse productos, no deberia dar errores. */
            int codigoProducto;
            do {
                System.out.println("Ingrese el código del producto a modificar:");
                codigoProducto = sc.nextInt();
            } while (codigoProducto < 1 || codigoProducto > productos.size());

            //Variables para asignar los nuevos atributos de mi Producto seleccionado
            String nombre;
            double precio;
            int codigoFabricante;

            //Input de los nuevos atributos
            System.out.println("\nA continuación, ingrese los datos actualizados del producto...");
            do {
                System.out.println("Ingrese el nuevo nombre:");
                nombre = sc.next();
            } while (nombre.trim().isEmpty());

            do {
                System.out.println("Ingrese el nuevo precio");
                precio = sc.nextDouble();
            } while (precio < 0);

            do {
                fabricanteService.imprimirFabricantes();
                System.out.println("\nIngrese el nuevo código de fabricante del producto:");
                codigoFabricante = sc.nextInt();
            } while (codigoFabricante < 1 || codigoFabricante > fabricanteService.cantidadFabricantes());

            //Armado del Producto con sus atributos modificados
            Producto nuevoProducto = new Producto();
            nuevoProducto.setCodigo(codigoProducto);
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);
            nuevoProducto.setCodigoFabricante(codigoFabricante);

            //Envio del Objeto al método de mi entidad DAO
            dao.modificarProductoSegunId(nuevoProducto);

            System.out.println("¡Producto modificado con éxito!");

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al intentar modificar un producto existente :3");
            System.out.println(e.getMessage());
        }

    }
}
