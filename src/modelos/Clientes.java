package modelos;

public class Clientes {

    private int idCliente;
    private String nombre;
    private String apellidos;
    private String nif;
    private String telefono;
    private String email;
    private String tipo;
    private int presupuestoMin;
    private int presupuestoMax;
    private int dormitoriosMin;
    private int dormitoriosMax;
    private int terrazaBalcon;
    private int aparcamiento;
    private int piscina;
    private int ascensor;
    private String poblacion;
    private String provincia;
    private int idUsuario;

    public Clientes() {
    }

    public Clientes(String nombre, String apellidos, String nif, String telefono, String email, String tipo, int presupuestoMin, int presupuestoMax, int dormitoriosMin, int dormitoriosMax, int terrazaBalcon, int aparcamiento, int piscina, int ascensor, String poblacion, String provincia, int idUsuario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.presupuestoMin = presupuestoMin;
        this.presupuestoMax = presupuestoMax;
        this.dormitoriosMin = dormitoriosMin;
        this.dormitoriosMax = dormitoriosMax;
        this.terrazaBalcon = terrazaBalcon;
        this.aparcamiento = aparcamiento;
        this.piscina = piscina;
        this.ascensor = ascensor;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.idUsuario = idUsuario;
    }

    public Clientes(int idCliente, String nombre, String apellidos, String nif, String telefono, String email, String tipo, int presupuestoMin, int presupuestoMax, int dormitoriosMin, int dormitoriosMax, int terrazaBalcon, int aparcamiento, int piscina, int ascensor, String poblacion, String provincia, int idUsuario) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.presupuestoMin = presupuestoMin;
        this.presupuestoMax = presupuestoMax;
        this.dormitoriosMin = dormitoriosMin;
        this.dormitoriosMax = dormitoriosMax;
        this.terrazaBalcon = terrazaBalcon;
        this.aparcamiento = aparcamiento;
        this.piscina = piscina;
        this.ascensor = ascensor;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPresupuestoMin() {
        return this.presupuestoMin;
    }

    public void setPresupuestoMin(int presupuestoMin) {
        this.presupuestoMin = presupuestoMin;
    }

    public int getPresupuestoMax() {
        return this.presupuestoMax;
    }

    public void setPresupuestoMax(int presupuestoMax) {
        this.presupuestoMax = presupuestoMax;
    }

    public int getDormitoriosMin() {
        return this.dormitoriosMin;
    }

    public void setDormitoriosMin(int dormitoriosMin) {
        this.dormitoriosMin = dormitoriosMin;
    }

    public int getDormitoriosMax() {
        return this.dormitoriosMax;
    }

    public void setDormitoriosMax(int dormitoriosMax) {
        this.dormitoriosMax = dormitoriosMax;
    }

    public int getTerrazaBalcon() {
        return this.terrazaBalcon;
    }

    public void setTerrazaBalcon(int terrazaBalcon) {
        this.terrazaBalcon = terrazaBalcon;
    }

    public int getAparcamiento() {
        return this.aparcamiento;
    }

    public void setAparcamiento(int aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    public int getPiscina() {
        return this.piscina;
    }

    public void setPiscina(int piscina) {
        this.piscina = piscina;
    }

    public int getAscensor() {
        return this.ascensor;
    }

    public void setAscensor(int ascensor) {
        this.ascensor = ascensor;
    }

    public String getPoblacion() {
        return this.poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
