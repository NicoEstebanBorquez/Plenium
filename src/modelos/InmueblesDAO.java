package modelos;

import gestionBD.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InmueblesDAO {

    private final String SQL_SELECT = "SELECT id_inmueble, nombre, tipo, precio, superficie_construida, superficie_total, direccion, poblacion, provincia, categoria,"
            + "fecha_publicacion, dormitorios, banos, amueblado, semi_amueblado, no_amueblado, cocina_equipada, aire_acondicionado, calefaccion_central, gas_natural,"
            + "placas_solares, armarios_empotrados, terraza_balcon, piscina, aparcamiento, ascensor, trastero, jardin,"
            + "propietario_nombre, propietario_apellidos, propietario_nif, propietario_telefono, propietario_email, id_usuario FROM inmuebles";

    private final String SQL_SELECT_ID = "SELECT id_inmueble, nombre, tipo, precio, superficie_construida, superficie_total, direccion, poblacion, provincia, categoria,"
            + "fecha_publicacion, dormitorios, banos, amueblado, semi_amueblado, no_amueblado, cocina_equipada, aire_acondicionado, calefaccion_central, gas_natural,"
            + "placas_solares, armarios_empotrados, terraza_balcon, piscina, aparcamiento, ascensor, trastero, jardin,"
            + "propietario_nombre, propietario_apellidos, propietario_nif, propietario_telefono, propietario_email, id_usuario FROM inmuebles WHERE id_inmueble = ?";

    private static final String SQL_INSERT = "INSERT INTO inmuebles (nombre, tipo, precio, superficie_construida, superficie_total, direccion, poblacion, provincia, categoria,"
            + "fecha_publicacion, dormitorios, banos, amueblado, semi_amueblado, no_amueblado, cocina_equipada, aire_acondicionado, calefaccion_central, gas_natural,"
            + "placas_solares, armarios_empotrados, terraza_balcon, piscina, aparcamiento, ascensor, trastero, jardin,"
            + "propietario_nombre, propietario_apellidos, propietario_nif, propietario_telefono, propietario_email, id_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_UPDATE = "UPDATE inmuebles SET nombre=?, tipo, precio=?, superficie_construida=?, superficie_total=?, direccion=?, poblacion=?, provincia=?, categoria=?,"
            + "fecha_publicacion=?, dormitorios=?, banos=?, amueblado=?, semi_amueblado=?, no_amueblado=?, cocina_equipada=?, aire_acondicionado=?, calefaccion_central=?, gas_natural=?,"
            + "placas_solares=?, armarios_empotrados=?, terraza_balcon=?, piscina=?, aparcamiento=?, ascensor=?, trastero=?, jardin=?,"
            + "propietario_nombre=?, propietario_apellidos=?, propietario_nif=?, propietario_telefono=?, propietario_email=?, id_usuario=? WHERE id_inmueble=?";

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
                int tipo = rs.getInt(3);
                double precio = rs.getDouble(4);
                double superficieConstruida = rs.getDouble(5);
                double superficieTotal = rs.getDouble(6);
                String direccion = rs.getString(7);
                String poblacion = rs.getString(8);
                String provincia = rs.getString(9);
                int categoria = rs.getInt(10);
                Date fechaPublicacion = rs.getDate(11);
                int dormitorios = rs.getInt(12);
                int banos = rs.getInt(13);
                int amueblado = rs.getInt(14);
                int semiAmueblado = rs.getInt(15);
                int noAmueblado = rs.getInt(16);
                int cocinaEquipada = rs.getInt(17);
                int aireAcondicionado = rs.getInt(18);
                int calefaccionCentral = rs.getInt(19);
                int gasNatural = rs.getInt(20);
                int placasSolares = rs.getInt(21);
                int armariosEmpotrados = rs.getInt(22);
                int terrazaBalcon = rs.getInt(23);
                int piscina = rs.getInt(24);
                int aparcamiento = rs.getInt(25);
                int ascensor = rs.getInt(26);
                int trastero = rs.getInt(27);
                int jardin = rs.getInt(28);
                String propietarioNombre = rs.getString(29);
                String propietarioApellidos = rs.getString(30);
                String propietarioNif = rs.getString(31);
                String propietarioTelefono = rs.getString(32);
                String propietarioEmail = rs.getString(33);
                int idUsuario = rs.getInt(34);

                inmueble = new Inmuebles(idInmueble, nombre, tipo, precio, superficieConstruida, superficieTotal, direccion, poblacion, provincia, categoria, fechaPublicacion, dormitorios, banos, amueblado, semiAmueblado, noAmueblado, cocinaEquipada, aireAcondicionado, calefaccionCentral, gasNatural, placasSolares, armariosEmpotrados, terrazaBalcon, piscina, aparcamiento, ascensor, trastero, jardin, propietarioNombre, propietarioApellidos, propietarioNif, propietarioTelefono, propietarioEmail, idUsuario);
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
                int tipo = rs.getInt(3);
                double precio = rs.getDouble(4);
                double superficieConstruida = rs.getDouble(5);
                double superficieTotal = rs.getDouble(6);
                String direccion = rs.getString(7);
                String poblacion = rs.getString(8);
                String provincia = rs.getString(9);
                int categoria = rs.getInt(10);
                Date fechaPublicacion = rs.getDate(11);
                int dormitorios = rs.getInt(12);
                int banos = rs.getInt(13);
                int amueblado = rs.getInt(14);
                int semiAmueblado = rs.getInt(15);
                int noAmueblado = rs.getInt(16);
                int cocinaEquipada = rs.getInt(17);
                int aireAcondicionado = rs.getInt(18);
                int calefaccionCentral = rs.getInt(19);
                int gasNatural = rs.getInt(20);
                int placasSolares = rs.getInt(21);
                int armariosEmpotrados = rs.getInt(22);
                int terrazaBalcon = rs.getInt(23);
                int piscina = rs.getInt(24);
                int aparcamiento = rs.getInt(25);
                int ascensor = rs.getInt(26);
                int trastero = rs.getInt(27);
                int jardin = rs.getInt(28);
                String propietarioNombre = rs.getString(29);
                String propietarioApellidos = rs.getString(30);
                String propietarioNif = rs.getString(31);
                String propietarioTelefono = rs.getString(32);
                String propietarioEmail = rs.getString(33);
                int idUsuario = rs.getInt(34);

                inmueble.setIdInmueble(idInmueble);
                inmueble.setNombre(nombre);
                inmueble.setTipo(tipo);
                inmueble.setPrecio(precio);
                inmueble.setSuperficieConstruida(superficieConstruida);
                inmueble.setSuperficieTotal(superficieTotal);
                inmueble.setDireccion(direccion);
                inmueble.setPoblacion(poblacion);
                inmueble.setProvincia(provincia);
                inmueble.setCategoria(categoria);
                inmueble.setFechaPublicacion(fechaPublicacion);
                inmueble.setDormitorios(dormitorios);
                inmueble.setBanos(banos);
                inmueble.setAmueblado(amueblado);
                inmueble.setSemiAmueblado(semiAmueblado);
                inmueble.setNoAmueblado(noAmueblado);
                inmueble.setCocinaEquipada(cocinaEquipada);
                inmueble.setAireAcondicionado(aireAcondicionado);
                inmueble.setCalefaccionCentral(calefaccionCentral);
                inmueble.setGasNatural(gasNatural);
                inmueble.setPlacasSolares(placasSolares);
                inmueble.setArmariosEmpotrados(armariosEmpotrados);
                inmueble.setTerrazaBalcon(terrazaBalcon);
                inmueble.setPiscina(piscina);
                inmueble.setAparcamiento(aparcamiento);
                inmueble.setAscensor(ascensor);
                inmueble.setTrastero(trastero);
                inmueble.setJardin(jardin);
                inmueble.setPropietarioNombre(propietarioNombre);
                inmueble.setPropietarioApellidos(propietarioApellidos);
                inmueble.setPropietarioNif(propietarioNif);
                inmueble.setPropietarioTelefono(propietarioTelefono);
                inmueble.setPropietarioEmail(propietarioEmail);
                inmueble.setIdUsuario(idUsuario);
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

    public int insertar(Inmuebles inmueble) {

        Connection cn = null;
        PreparedStatement ps = null;
        int elementosInsertados = 0;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_INSERT);

            ps.setString(1, inmueble.getNombre());
            ps.setInt(2, inmueble.getTipo());
            ps.setDouble(3, inmueble.getPrecio());
            ps.setDouble(4, inmueble.getSuperficieConstruida());
            ps.setDouble(5, inmueble.getSuperficieTotal());
            ps.setString(6, inmueble.getDireccion());
            ps.setString(7, inmueble.getPoblacion());
            ps.setString(8, inmueble.getProvincia());
            ps.setString(9, inmueble.getPoblacion());
            ps.setInt(10, inmueble.getCategoria());
            ps.setDate(11, inmueble.getFechaPublicacion());
            ps.setInt(12, inmueble.getDormitorios());
            ps.setInt(13, inmueble.getBanos());
            ps.setInt(14, inmueble.getAmueblado());
            ps.setInt(15, inmueble.getSemiAmueblado());
            ps.setInt(16, inmueble.getNoAmueblado());
            ps.setInt(17, inmueble.getCocinaEquipada());
            ps.setInt(18, inmueble.getAireAcondicionado());
            ps.setInt(19, inmueble.getCalefaccionCentral());
            ps.setInt(20, inmueble.getGasNatural());
            ps.setInt(21, inmueble.getPlacasSolares());
            ps.setInt(22, inmueble.getArmariosEmpotrados());
            ps.setInt(23, inmueble.getTerrazaBalcon());
            ps.setInt(24, inmueble.getPiscina());
            ps.setInt(25, inmueble.getAparcamiento());
            ps.setInt(26, inmueble.getAscensor());
            ps.setInt(27, inmueble.getTrastero());
            ps.setInt(28, inmueble.getJardin());
            ps.setString(29, inmueble.getPropietarioNombre());
            ps.setString(30, inmueble.getPropietarioApellidos());
            ps.setString(31, inmueble.getPropietarioNif());
            ps.setString(32, inmueble.getPropietarioTelefono());
            ps.setString(33, inmueble.getPropietarioEmail());
            ps.setInt(34, inmueble.getIdUsuario());

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
            ps.setString(1, inmuebleSeleccionado.getNombre());
            ps.setInt(2, inmuebleSeleccionado.getTipo());
            ps.setDouble(3, inmuebleSeleccionado.getPrecio());
            ps.setDouble(4, inmuebleSeleccionado.getSuperficieConstruida());
            ps.setDouble(5, inmuebleSeleccionado.getSuperficieTotal());
            ps.setString(6, inmuebleSeleccionado.getDireccion());
            ps.setString(7, inmuebleSeleccionado.getPoblacion());
            ps.setString(8, inmuebleSeleccionado.getProvincia());
            ps.setString(9, inmuebleSeleccionado.getPoblacion());
            ps.setInt(10, inmuebleSeleccionado.getCategoria());
            ps.setDate(11, inmuebleSeleccionado.getFechaPublicacion());
            ps.setInt(12, inmuebleSeleccionado.getDormitorios());
            ps.setInt(13, inmuebleSeleccionado.getBanos());
            ps.setInt(14, inmuebleSeleccionado.getAmueblado());
            ps.setInt(15, inmuebleSeleccionado.getSemiAmueblado());
            ps.setInt(16, inmuebleSeleccionado.getNoAmueblado());
            ps.setInt(17, inmuebleSeleccionado.getCocinaEquipada());
            ps.setInt(18, inmuebleSeleccionado.getAireAcondicionado());
            ps.setInt(19, inmuebleSeleccionado.getCalefaccionCentral());
            ps.setInt(20, inmuebleSeleccionado.getGasNatural());
            ps.setInt(21, inmuebleSeleccionado.getPlacasSolares());
            ps.setInt(22, inmuebleSeleccionado.getArmariosEmpotrados());
            ps.setInt(23, inmuebleSeleccionado.getTerrazaBalcon());
            ps.setInt(24, inmuebleSeleccionado.getPiscina());
            ps.setInt(25, inmuebleSeleccionado.getAparcamiento());
            ps.setInt(26, inmuebleSeleccionado.getAscensor());
            ps.setInt(27, inmuebleSeleccionado.getTrastero());
            ps.setInt(28, inmuebleSeleccionado.getJardin());
            ps.setString(29, inmuebleSeleccionado.getPropietarioNombre());
            ps.setString(30, inmuebleSeleccionado.getPropietarioApellidos());
            ps.setString(31, inmuebleSeleccionado.getPropietarioNif());
            ps.setString(32, inmuebleSeleccionado.getPropietarioTelefono());
            ps.setString(33, inmuebleSeleccionado.getPropietarioEmail());
            ps.setInt(34, inmuebleSeleccionado.getIdUsuario());

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
