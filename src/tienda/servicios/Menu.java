package tienda.servicios;

import java.util.Scanner;

/**
 *
 * @author Matias Luca Soto
 */
public final class Menu {

    //ATRIBUTO Scanner, ProductoService y FabricanteService
    private final Scanner sc;
    private final ProductoService productoService;
    private final FabricanteService fabricanteService;

    //Constructor con atributos inicializados
    public Menu() {
        this.sc = new Scanner(System.in).useDelimiter("\n");
        this.productoService = new ProductoService();
        this.fabricanteService = new FabricanteService();
    }

    /**
     * Imprime por pantalla el menú de todas las opciones disponibles
     */
    private void mostrarMenu() {

        System.out.println("\n---------------------------"
                + "\nMENU"
                + "\n---------------------------"
                + "\n1. Ver el nombre de todos los productos"
                + "\n2. Ver todos los nombres y precios de los productos"
                + "\n3. Ver productos entre $120 y $202"
                + "\n4. Buscar todos los portátiles en stock"
                + "\n5. Ver el producto más barato"
                + "\n6. Ingresar un producto a la base de datos"
                + "\n7. Ingresar un fabricante a la base de datos"
                + "\n8. Editar un producto existente"
                + "\n9. Salir");

    }

    /**
     * Permite que el Usuario ingrese una opción válida según el menú
     *
     * @return opción a ejecutar por el usuario
     */
    private int elegirOpcion() {
        int opc;
        do {
            System.out.println("\nIngrese el número de la opción deseada...");
            opc = sc.nextInt();
        } while (opc < 1 || opc > 9);
        return opc;
    }

    /**
     * Ejecuta la opción indicada por el usuario llamando al método de la Clase Service correspondiente
     *
     * @param opcion elegido por el usuario
     */
    private void ejectuarOpc(int opcion) {

        switch (opcion) {
            case 1:
                productoService.listarNombres();
                break;
            case 2:
                productoService.listarNombresPrecios();
                break;
            case 3:
                productoService.listarProductosEntre120y202();
                break;
            case 4:
                productoService.listarPortatiles();
                break;
            case 5:
                productoService.productoMasBarato();
                break;
            case 6:
                productoService.ingresarProducto();
                break;
            case 7:
                fabricanteService.ingresarFabricante();
                break;
            case 8:
                productoService.modificarProductoPorId();
                break;
            case 9:
                System.out.println("¡Que le vaya bonito, mijo!");
                break;
        }

    }

    /**
     * Método principal que muestra el menú, toma una opción válida del usuario y ejecuta el método correspondiente
     */
    public void iniciarMenu() {

        System.out.println("¡Bienvenido a TechHouse!");

        int opcion;

        do {
            mostrarMenu();
            opcion = elegirOpcion();
            ejectuarOpc(opcion);
        } while (opcion != 9); //Condición de salida

        System.out.println("¡Adios!");

    }

}
