package modelos;

import java.sql.Date;

public class Inmuebles {

    private int idInmueble;
    private String nombre;
    private String tipo;
/*
Piso
Casa/Chalet
Garaje
Terreno
Oficina 
Local comercial
*/
    private int precio;
    private double superficieUtil;
    private double superficieTotal;
    private String direccion;
    private String poblacion;
    private String provincia;
    private Date fechaPublicacion;
    private int dormitorios;
    private int banos;
    private int amueblado;
    private int semiAmueblado;
    private int noAmueblado;
    private int cocinaEquipada;
    private int aireAcondicionado;
    private int calefaccionCentral;
    private int gasNatural;
    private int placasSolares;
    private int armariosEmpotrados;
    private int terrazaBalcon;
    private int piscina;
    private int aparcamiento;
    private int ascensor;
    private int trastero;
    private int jardin;
    private String propietarioNombre;
    private String propietarioApellidos;
    private String propietarioNif;
    private String propietarioTelefono;
    private String propietarioEmail;
    private int idUsuario;

    public Inmuebles() {
    }

    public Inmuebles(String nombre, String tipo, int precio, double superficieUtil, double superficieTotal, String direccion, String poblacion, String provincia, Date fechaPublicacion, int dormitorios, int banos, int amueblado, int semiAmueblado, int noAmueblado, int cocinaEquipada, int aireAcondicionado, int calefaccionCentral, int gasNatural, int placasSolares, int armariosEmpotrados, int terrazaBalcon, int piscina, int aparcamiento, int ascensor, int trastero, int jardin, String propietarioNombre, String propietarioApellidos, String propietarioNif, String propietarioTelefono, String propietarioEmail, int idUsuario) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.superficieUtil = superficieUtil;
        this.superficieTotal = superficieTotal;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.fechaPublicacion = fechaPublicacion;
        this.dormitorios = dormitorios;
        this.banos = banos;
        this.amueblado = amueblado;
        this.semiAmueblado = semiAmueblado;
        this.noAmueblado = noAmueblado;
        this.cocinaEquipada = cocinaEquipada;
        this.aireAcondicionado = aireAcondicionado;
        this.calefaccionCentral = calefaccionCentral;
        this.gasNatural = gasNatural;
        this.placasSolares = placasSolares;
        this.armariosEmpotrados = armariosEmpotrados;
        this.terrazaBalcon = terrazaBalcon;
        this.piscina = piscina;
        this.aparcamiento = aparcamiento;
        this.ascensor = ascensor;
        this.trastero = trastero;
        this.jardin = jardin;
        this.propietarioNombre = propietarioNombre;
        this.propietarioApellidos = propietarioApellidos;
        this.propietarioNif = propietarioNif;
        this.propietarioTelefono = propietarioTelefono;
        this.propietarioEmail = propietarioEmail;
        this.idUsuario = idUsuario;
    }

    public Inmuebles(int idInmueble, String nombre, String tipo, int precio, double superficieUtil, double superficieTotal, String direccion, String poblacion, String provincia, Date fechaPublicacion, int dormitorios, int banos, int amueblado, int semiAmueblado, int noAmueblado, int cocinaEquipada, int aireAcondicionado, int calefaccionCentral, int gasNatural, int placasSolares, int armariosEmpotrados, int terrazaBalcon, int piscina, int aparcamiento, int ascensor, int trastero, int jardin, String propietarioNombre, String propietarioApellidos, String propietarioNif, String propietarioTelefono, String propietarioEmail, int idUsuario) {
        this.idInmueble = idInmueble;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.superficieUtil = superficieUtil;
        this.superficieTotal = superficieTotal;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.fechaPublicacion = fechaPublicacion;
        this.dormitorios = dormitorios;
        this.banos = banos;
        this.amueblado = amueblado;
        this.semiAmueblado = semiAmueblado;
        this.noAmueblado = noAmueblado;
        this.cocinaEquipada = cocinaEquipada;
        this.aireAcondicionado = aireAcondicionado;
        this.calefaccionCentral = calefaccionCentral;
        this.gasNatural = gasNatural;
        this.placasSolares = placasSolares;
        this.armariosEmpotrados = armariosEmpotrados;
        this.terrazaBalcon = terrazaBalcon;
        this.piscina = piscina;
        this.aparcamiento = aparcamiento;
        this.ascensor = ascensor;
        this.trastero = trastero;
        this.jardin = jardin;
        this.propietarioNombre = propietarioNombre;
        this.propietarioApellidos = propietarioApellidos;
        this.propietarioNif = propietarioNif;
        this.propietarioTelefono = propietarioTelefono;
        this.propietarioEmail = propietarioEmail;
        this.idUsuario = idUsuario;
    }

    public int getIdInmueble() {
        return this.idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecio() {
        return this.precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public double getSuperficieUtil() {
        return this.superficieUtil;
    }

    public void setSuperficieUtil(double superficieUtil) {
        this.superficieUtil = superficieUtil;
    }

    public double getSuperficieTotal() {
        return this.superficieTotal;
    }

    public void setSuperficieTotal(double superficieTotal) {
        this.superficieTotal = superficieTotal;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public Date getFechaPublicacion() {
        return this.fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getDormitorios() {
        return this.dormitorios;
    }

    public void setDormitorios(int dormitorios) {
        this.dormitorios = dormitorios;
    }

    public int getBanos() {
        return this.banos;
    }

    public void setBanos(int banos) {
        this.banos = banos;
    }

    public int getAmueblado() {
        return this.amueblado;
    }

    public void setAmueblado(int amueblado) {
        this.amueblado = amueblado;
    }

    public int getSemiAmueblado() {
        return this.semiAmueblado;
    }

    public void setSemiAmueblado(int semiAmueblado) {
        this.semiAmueblado = semiAmueblado;
    }

    public int getNoAmueblado() {
        return this.noAmueblado;
    }

    public void setNoAmueblado(int noAmueblado) {
        this.noAmueblado = noAmueblado;
    }

    public int getCocinaEquipada() {
        return this.cocinaEquipada;
    }

    public void setCocinaEquipada(int cocinaEquipada) {
        this.cocinaEquipada = cocinaEquipada;
    }

    public int getAireAcondicionado() {
        return this.aireAcondicionado;
    }

    public void setAireAcondicionado(int aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public int getCalefaccionCentral() {
        return this.calefaccionCentral;
    }

    public void setCalefaccionCentral(int calefaccionCentral) {
        this.calefaccionCentral = calefaccionCentral;
    }

    public int getGasNatural() {
        return this.gasNatural;
    }

    public void setGasNatural(int gasNatural) {
        this.gasNatural = gasNatural;
    }

    public int getPlacasSolares() {
        return this.placasSolares;
    }

    public void setPlacasSolares(int placasSolares) {
        this.placasSolares = placasSolares;
    }

    public int getArmariosEmpotrados() {
        return this.armariosEmpotrados;
    }

    public void setArmariosEmpotrados(int armariosEmpotrados) {
        this.armariosEmpotrados = armariosEmpotrados;
    }

    public int getTerrazaBalcon() {
        return this.terrazaBalcon;
    }

    public void setTerrazaBalcon(int terrazaBalcon) {
        this.terrazaBalcon = terrazaBalcon;
    }

    public int getPiscina() {
        return this.piscina;
    }

    public void setPiscina(int piscina) {
        this.piscina = piscina;
    }

    public int getAparcamiento() {
        return this.aparcamiento;
    }

    public void setAparcamiento(int aparcamiento) {
        this.aparcamiento = aparcamiento;
    }

    public int getAscensor() {
        return this.ascensor;
    }

    public void setAscensor(int ascensor) {
        this.ascensor = ascensor;
    }

    public int getTrastero() {
        return this.trastero;
    }

    public void setTrastero(int trastero) {
        this.trastero = trastero;
    }

    public int getJardin() {
        return this.jardin;
    }

    public void setJardin(int jardin) {
        this.jardin = jardin;
    }

    public String getPropietarioNombre() {
        return propietarioNombre;
    }

    public void setPropietarioNombre(String propietarioNombre) {
        this.propietarioNombre = propietarioNombre;
    }

    public String getPropietarioApellidos() {
        return propietarioApellidos;
    }

    public void setPropietarioApellidos(String propietarioApellidos) {
        this.propietarioApellidos = propietarioApellidos;
    }

    public String getPropietarioNif() {
        return propietarioNif;
    }

    public void setPropietarioNif(String propietarioNif) {
        this.propietarioNif = propietarioNif;
    }

    public String getPropietarioTelefono() {
        return propietarioTelefono;
    }

    public void setPropietarioTelefono(String propietarioTelefono) {
        this.propietarioTelefono = propietarioTelefono;
    }

    public String getPropietarioEmail() {
        return propietarioEmail;
    }

    public void setPropietarioEmail(String propietarioEmail) {
        this.propietarioEmail = propietarioEmail;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
