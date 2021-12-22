package modelos;

import gestionBD.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InmueblesDAO {
//id_inmueble, nombre, tipo, precio, superficie_util, superficie_total, direccion, poblacion, provincia, fecha_publicacion, dormitorios, banos, amueblado, semi_amueblado, no_amueblado, cocina_equipada, aire_acondicionado, calefaccion_central, gas_natural, placas_solares, armarios_empotrados, terraza_balcon, piscina, aparcamiento, ascensor, trastero, jardin, propietario_nombre, propietario_apellidos, propietario_nif, propietario_telefono, propietario_email, id_usuario

    private final String SQL_SELECT = "SELECT * FROM inmuebles";

    private final String SQL_SELECT_ID = "SELECT * FROM inmuebles WHERE id_inmueble=?";
//                                                                                                         1     2                      3                   4                   5              6
    private final String SQL_SELECT_BUSQUEDA_AVANZADA = "SELECT * FROM `inmuebles` WHERE "
            + "(`precio` BETWEEN ? AND ?) AND "
            + "(`dormitorios`>=?) AND "
            + "(`poblacion`=?) AND "
            + "(`provincia`=?) AND "
            + "(`tipo`=?)";
    //private final String SQL_SELECT_BUSQUEDA_AVANZADA = "SELECT * FROM `inmuebles` WHERE `precio` BETWEEN ? AND ? AND `dormitorios`>=? AND `poblacion`=? AND `provincia`=? AND `tipo`=?";
    //SELECT * FROM `inmuebles` WHERE `precio` BETWEEN 100000 AND 200000 AND `dormitorios`>=2 AND `poblacion`='Velez-Malaga' AND `provincia`= 'Malaga' AND `tipo` = 'PISO'

    private final String SQL_INSERT = "INSERT INTO inmuebles VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE inmuebles SET nombre=?, tipo=?, precio=?, superficie_util=?, superficie_total=?, direccion=?, poblacion=?, provincia=?, fecha_publicacion=?, dormitorios=?, banos=?, amueblado=?, semi_amueblado=?, no_amueblado=?, cocina_equipada=?, aire_acondicionado=?, calefaccion_central=?, gas_natural=?, placas_solares=?, armarios_empotrados=?, terraza_balcon=?, piscina=?, aparcamiento=?, ascensor=?, trastero=?, jardin=?, propietario_nombre=?, propietario_apellidos=?, propietario_nif=?, propietario_telefono=?, propietario_email=?, id_usuario=? WHERE id_inmueble=?";

    private final String SQL_DELETE = "DELETE FROM inmuebles WHERE id_inmueble=?";

    public List listar() {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Inmuebles> lista = new ArrayList<>();
        Inmuebles inmueble = null;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idInmueble = rs.getInt(1);
                String nombre = rs.getString(2);
                String tipo = rs.getString(3);
                double precio = rs.getDouble(4);
                double superficieUtil = rs.getDouble(5);
                double superficieTotal = rs.getDouble(6);
                String direccion = rs.getString(7);
                String poblacion = rs.getString(8);
                String provincia = rs.getString(9);
                Date fechaPublicacion = rs.getDate(10);
                int dormitorios = rs.getInt(11);
                int banos = rs.getInt(12);
                int amueblado = rs.getInt(13);
                int semiAmueblado = rs.getInt(14);
                int noAmueblado = rs.getInt(15);
                int cocinaEquipada = rs.getInt(16);
                int aireAcondicionado = rs.getInt(17);
                int calefaccionCentral = rs.getInt(18);
                int gasNatural = rs.getInt(19);
                int placasSolares = rs.getInt(20);
                int armariosEmpotrados = rs.getInt(21);
                int terrazaBalcon = rs.getInt(22);
                int piscina = rs.getInt(23);
                int aparcamiento = rs.getInt(24);
                int ascensor = rs.getInt(25);
                int trastero = rs.getInt(26);
                int jardin = rs.getInt(27);
                String propietarioNombre = rs.getString(28);
                String propietarioApellidos = rs.getString(29);
                String propietarioNif = rs.getString(30);
                String propietarioTelefono = rs.getString(31);
                String propietarioEmail = rs.getString(32);
                int idUsuario = rs.getInt(33);

                inmueble = new Inmuebles(idInmueble, nombre, tipo, precio, superficieUtil, superficieTotal, direccion, poblacion, provincia, fechaPublicacion, dormitorios, banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, piscina, aparcamiento, ascensor, trastero, jardin, propietarioNombre, propietarioApellidos, propietarioNif, propietarioTelefono, propietarioEmail, idUsuario);
                lista.add(inmueble);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return lista;
    }

    public Inmuebles obtenerPorId(int inmuebleSeleccionado) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Inmuebles inmueble = new Inmuebles();

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, inmuebleSeleccionado);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idInmueble = rs.getInt(1);
                String nombre = rs.getString(2);
                String tipo = rs.getString(3);
                double precio = rs.getDouble(4);
                double superficieUtil = rs.getDouble(5);
                double superficieTotal = rs.getDouble(6);
                String direccion = rs.getString(7);
                String poblacion = rs.getString(8);
                String provincia = rs.getString(9);
                Date fechaPublicacion = rs.getDate(10);
                int dormitorios = rs.getInt(11);
                int banos = rs.getInt(12);
                int amueblado = rs.getInt(13);
                int semiAmueblado = rs.getInt(14);
                int noAmueblado = rs.getInt(15);
                int cocinaEquipada = rs.getInt(16);
                int aireAcondicionado = rs.getInt(17);
                int calefaccionCentral = rs.getInt(18);
                int gasNatural = rs.getInt(19);
                int placasSolares = rs.getInt(20);
                int armariosEmpotrados = rs.getInt(21);
                int terrazaBalcon = rs.getInt(22);
                int piscina = rs.getInt(23);
                int aparcamiento = rs.getInt(24);
                int ascensor = rs.getInt(25);
                int trastero = rs.getInt(26);
                int jardin = rs.getInt(27);
                String propietarioNombre = rs.getString(28);
                String propietarioApellidos = rs.getString(29);
                String propietarioNif = rs.getString(30);
                String propietarioTelefono = rs.getString(31);
                String propietarioEmail = rs.getString(32);
                int idUsuario = rs.getInt(33);

                inmueble = new Inmuebles(idInmueble, nombre, tipo, precio, superficieUtil, superficieTotal, direccion, poblacion, provincia, fechaPublicacion, dormitorios, banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, piscina, aparcamiento, ascensor, trastero, jardin, propietarioNombre, propietarioApellidos, propietarioNif, propietarioTelefono, propietarioEmail, idUsuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return inmueble;
    }

    public List listarBusquedaAvanzada(int precioMin, int precioMax, int dormitoriosMin, String poblacion_b, String provincia_b, String tipo_b) {

        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Inmuebles> lista = new ArrayList<>();
        Inmuebles inmueble = null;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT_BUSQUEDA_AVANZADA);

            ps.setInt(1, precioMin);
            ps.setInt(2, precioMax);
            ps.setInt(3, dormitoriosMin);
            ps.setString(4, poblacion_b);
            ps.setString(5, provincia_b);
            ps.setString(6, tipo_b);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idInmueble = rs.getInt(1);
                String nombre = rs.getString(2);
                String tipo = rs.getString(3);
                double precio = rs.getDouble(4);
                double superficieUtil = rs.getDouble(5);
                double superficieTotal = rs.getDouble(6);
                String direccion = rs.getString(7);
                String poblacion = rs.getString(8);
                String provincia = rs.getString(9);
                Date fechaPublicacion = rs.getDate(10);
                int dormitorios = rs.getInt(11);
                int banos = rs.getInt(12);
                int amueblado = rs.getInt(13);
                int semiAmueblado = rs.getInt(14);
                int noAmueblado = rs.getInt(15);
                int cocinaEquipada = rs.getInt(16);
                int aireAcondicionado = rs.getInt(17);
                int calefaccionCentral = rs.getInt(18);
                int gasNatural = rs.getInt(19);
                int placasSolares = rs.getInt(20);
                int armariosEmpotrados = rs.getInt(21);
                int terrazaBalcon = rs.getInt(22);
                int piscina = rs.getInt(23);
                int aparcamiento = rs.getInt(24);
                int ascensor = rs.getInt(25);
                int trastero = rs.getInt(26);
                int jardin = rs.getInt(27);
                String propietarioNombre = rs.getString(28);
                String propietarioApellidos = rs.getString(29);
                String propietarioNif = rs.getString(30);
                String propietarioTelefono = rs.getString(31);
                String propietarioEmail = rs.getString(32);
                int idUsuario = rs.getInt(33);

                inmueble = new Inmuebles(idInmueble, nombre, tipo, precio, superficieUtil, superficieTotal, direccion, poblacion, provincia, fechaPublicacion, dormitorios, banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, piscina, aparcamiento, ascensor, trastero, jardin, propietarioNombre, propietarioApellidos, propietarioNif, propietarioTelefono, propietarioEmail, idUsuario);
                lista.add(inmueble);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return lista;
    }

    public int insertar(Inmuebles inmueble) {
        Connection cn = null;
        PreparedStatement ps = null;
        int elementosInsertados = 0;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_INSERT);

            ps.setInt(1, 0);
            ps.setString(2, inmueble.getNombre());
            ps.setString(3, inmueble.getTipo());
            ps.setDouble(4, inmueble.getPrecio());
            ps.setDouble(5, inmueble.getSuperficieUtil());
            ps.setDouble(6, inmueble.getSuperficieTotal());
            ps.setString(7, inmueble.getDireccion());
            ps.setString(8, inmueble.getPoblacion());
            ps.setString(9, inmueble.getProvincia());
            ps.setDate(10, inmueble.getFechaPublicacion());
            ps.setInt(11, inmueble.getDormitorios());
            ps.setInt(12, inmueble.getBanos());
            ps.setInt(13, inmueble.getAmueblado());
            ps.setInt(14, inmueble.getSemiAmueblado());
            ps.setInt(15, inmueble.getNoAmueblado());
            ps.setInt(16, inmueble.getCocinaEquipada());
            ps.setInt(17, inmueble.getAireAcondicionado());
            ps.setInt(18, inmueble.getCalefaccionCentral());
            ps.setInt(19, inmueble.getGasNatural());
            ps.setInt(20, inmueble.getPlacasSolares());
            ps.setInt(21, inmueble.getArmariosEmpotrados());
            ps.setInt(22, inmueble.getTerrazaBalcon());
            ps.setInt(23, inmueble.getPiscina());
            ps.setInt(24, inmueble.getAparcamiento());
            ps.setInt(25, inmueble.getAscensor());
            ps.setInt(26, inmueble.getTrastero());
            ps.setInt(27, inmueble.getJardin());
            ps.setString(28, inmueble.getPropietarioNombre());
            ps.setString(29, inmueble.getPropietarioApellidos());
            ps.setString(30, inmueble.getPropietarioNif());
            ps.setString(31, inmueble.getPropietarioTelefono());
            ps.setString(32, inmueble.getPropietarioEmail());
            ps.setInt(33, inmueble.getIdUsuario());

            elementosInsertados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return elementosInsertados;
    }

    public int actualizar(Inmuebles inmuebleSeleccionado) {
        Connection cn = null;
        PreparedStatement ps = null;
        int elementosActualizados = 0;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_UPDATE);
            ps.setString(1, inmuebleSeleccionado.getNombre());
            ps.setString(2, inmuebleSeleccionado.getTipo());
            ps.setDouble(3, inmuebleSeleccionado.getPrecio());
            ps.setDouble(4, inmuebleSeleccionado.getSuperficieUtil());
            ps.setDouble(5, inmuebleSeleccionado.getSuperficieTotal());
            ps.setString(6, inmuebleSeleccionado.getDireccion());
            ps.setString(7, inmuebleSeleccionado.getPoblacion());
            ps.setString(8, inmuebleSeleccionado.getProvincia());
            ps.setDate(9, inmuebleSeleccionado.getFechaPublicacion());
            ps.setInt(10, inmuebleSeleccionado.getDormitorios());
            ps.setInt(11, inmuebleSeleccionado.getBanos());
            ps.setInt(12, inmuebleSeleccionado.getAmueblado());
            ps.setInt(13, inmuebleSeleccionado.getSemiAmueblado());
            ps.setInt(14, inmuebleSeleccionado.getNoAmueblado());
            ps.setInt(15, inmuebleSeleccionado.getCocinaEquipada());
            ps.setInt(16, inmuebleSeleccionado.getAireAcondicionado());
            ps.setInt(17, inmuebleSeleccionado.getCalefaccionCentral());
            ps.setInt(18, inmuebleSeleccionado.getGasNatural());
            ps.setInt(19, inmuebleSeleccionado.getPlacasSolares());
            ps.setInt(20, inmuebleSeleccionado.getArmariosEmpotrados());
            ps.setInt(21, inmuebleSeleccionado.getTerrazaBalcon());
            ps.setInt(22, inmuebleSeleccionado.getPiscina());
            ps.setInt(23, inmuebleSeleccionado.getAparcamiento());
            ps.setInt(24, inmuebleSeleccionado.getAscensor());
            ps.setInt(25, inmuebleSeleccionado.getTrastero());
            ps.setInt(26, inmuebleSeleccionado.getJardin());
            ps.setString(27, inmuebleSeleccionado.getPropietarioNombre());
            ps.setString(28, inmuebleSeleccionado.getPropietarioApellidos());
            ps.setString(29, inmuebleSeleccionado.getPropietarioNif());
            ps.setString(30, inmuebleSeleccionado.getPropietarioTelefono());
            ps.setString(31, inmuebleSeleccionado.getPropietarioEmail());
            ps.setInt(32, inmuebleSeleccionado.getIdUsuario());
            //Where:
            ps.setInt(33, inmuebleSeleccionado.getIdInmueble());

            elementosActualizados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return elementosActualizados;
    }

    public int eliminar(int id) {

        Connection cn = null;
        PreparedStatement ps = null;
        int eliminado = 0;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            eliminado = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return eliminado;
    }

    /*
    public List encontrarPorIdPropietario(int id) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Inmuebles> lista = new ArrayList<>();
        Inmuebles inmueble = null;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_POR_ID_PROPIETARIO);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idInmuebles = rs.getInt(1);
                String nombre = rs.getString(2);
                int capacidad = rs.getInt(3);
                int dormitorios = rs.getInt(4);
                int banos = rs.getInt(5);
                String terraza = rs.getString(6);
                String piscina = rs.getString(7);
                String aparcamiento = rs.getString(8);
                String direccion = rs.getString(9);
                String poblacion = rs.getString(10);
                String provincia = rs.getString(11);
                int idPropietario = rs.getInt(12);
                int idUsuario = rs.getInt(13);

                inmueble = new Inmuebles(idInmuebles, nombre, capacidad, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, idPropietario, idUsuario);
                lista.add(inmueble);
            }

        } catch (SQLException ex) {
            Logger.getLogger(InmueblesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return lista;
    }*/
 /*
   
     */
}
