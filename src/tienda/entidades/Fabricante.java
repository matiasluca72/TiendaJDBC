package tienda.entidades;

/**
 *
 * @author Matias Luca Soto
 */
public class Fabricante {

    //ATRIBUTOS
    private Integer codigo;
    private String nombre;

    //CONSTRUCTORES
    public Fabricante(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Fabricante() {
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

    //toString
    @Override
    public String toString() {
        return "Código: " + codigo + " - Nombre: " + nombre;
    }

}
