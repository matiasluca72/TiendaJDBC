package tienda.entidades;

import tienda.servicios.FabricanteService;

/**
 *
 * @author Matias Luca Soto
 */
public class Producto {

    //ATRIBUTOS
    private Integer codigo;
    private String nombre;
    private Double precio;
    private Integer codigoFabricante;

    //Fabricante Service
    private final FabricanteService fabricanteService;

    //CONSTRUCTORES
    public Producto(Integer codigo, String nombre, Double precio, Integer codigoFabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.codigoFabricante = codigoFabricante;
        fabricanteService = new FabricanteService();
    }

    public Producto() {
        fabricanteService = new FabricanteService();
    }

    //GETTERS & SETTERS
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(Integer codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    //toString
    /*Utiliza un try - catch por el uso de uno de los métodos de Fabricante Service que devuelve
    el nombre del fabricante como un String */
    @Override
    public String toString() {
        try {
            return "Código: " + codigo + " / Nombre: " + nombre + " / $" + precio + " / Fabricante: " + fabricanteService.devolverFabricante(getCodigoFabricante());
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println(ex.getMessage());
            return "Hubieron problemas, amigos :(";
        }
    }

}
