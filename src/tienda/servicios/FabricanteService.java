package tienda.servicios;

import java.util.Collection;
import java.util.Scanner;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

/**
 *
 * @author Matias Luca Soto
 */
public final class FabricanteService {

    //ATRIBUTOS - OBJETO DAO y Scanner
    private final FabricanteDAO dao;
    private final Scanner sc;

    //CONSTRUCTOR CON ATRIBUTOS INICIALIZADOS
    public FabricanteService() {
        this.dao = new FabricanteDAO();
        this.sc = new Scanner(System.in).useDelimiter("\n");
    }

    //MÉTODOS
    /**
     * Método que devuelve en forma de String el nombre del Fabricante correspondiente al codigo enviado como argumento
     *
     * @param codigoFabricante atributo código del Fabricante que se quiera saber su nombre
     * @return nombre del Fabricante correspondiente
     * @throws Exception
     */
    public String devolverFabricante(int codigoFabricante) throws Exception {
        return dao.devolverFabricante(codigoFabricante);
    }

    /**
     * Imprime por consola el toString de todos los fabricantes existentes en la tabla Fabricante
     */
    public void imprimirFabricantes() {

        try {

            //Guardo el ArrayList que devuelve el método de entidadDAO
            Collection<Fabricante> fabricantes = dao.listarFabricantes();

            //Output de los fabricantes
            System.out.println("\nLista de fabricantes:");
            for (Fabricante fabricante : fabricantes) {
                System.out.println(fabricante);
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Ups! Error buscando a los fabricantes xd"
                    + "\n" + e.getMessage());
        }

    }

    /**
     * Devuelve la cantidad de registros en la tabla Fabricante en formato int
     *
     * @return cantidad de Fabricantes existentes en su tabla
     * @throws Exception
     */
    public int cantidadFabricantes() throws Exception {

        //Devuelvo el size() del ArrayList devuelto por el método listarFabricantes()
        return dao.listarFabricantes().size();
    }

    /**
     * g) Ingresar un fabricante a la base de datos
     */
    public void ingresarFabricante() {

        try {

            //Input del nombre del nuevo fabricante
            String nombre;
            do {
                System.out.println("Ingrese el nombre del fabricante:");
                nombre = sc.next();
            } while (nombre.trim().isEmpty());

            //Envio el String al método de la entidad DAO
            dao.ingresarFabricante(nombre);

            System.out.println("¡Fabricante ingresado con éxito!");

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error al intentar ingresar un nuevo Fabricante :B");
            System.out.println(e.getMessage());
        }

    }

}
