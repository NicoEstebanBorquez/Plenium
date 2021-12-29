package modelos;

public class Oficinas {

    private int idOficina;
    private String nombre;
    private String telefono;
    private String email;

    public Oficinas() {
    }

    public Oficinas(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Oficinas(int idOficina, String nombre, String telefono, String email) {
        this.idOficina = idOficina;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
