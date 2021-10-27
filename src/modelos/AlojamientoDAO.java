package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlojamientoDAO {

    private static final String SQL_SELECT = "SELECT id_alojamiento, nombre, plazas, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario FROM alojamiento";
    private static final String SQL_SELECT_POR_ID = "SELECT id_alojamiento, nombre, plazas, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario FROM alojamiento WHERE id_alojamiento=?";
    private static final String SQL_SELECT_POR_ID_PROPIETARIO = "SELECT id_alojamiento, nombre, plazas, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario FROM alojamiento WHERE id_propietario=?";
    private static final String SQL_INSERT = "INSERT INTO alojamiento (nombre, plazas, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE alojamiento SET nombre=?, plazas=?, dormitorios=?, banos=?, terraza=?, piscina=?, aparcamiento=?, direccion=?, poblacion=?, provincia=?, id_propietario=?, id_usuario=? WHERE id_alojamiento=?";
    private static final String SQL_DELETE = "DELETE FROM alojamiento WHERE id_alojamiento=?";

    /*
    public List listar() {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alojamiento> lista = new ArrayList<>();
        Alojamiento alojamiento = null;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idAlojamiento = rs.getInt(1);
                String nombre = rs.getString(2);
                int plazas = rs.getInt(3);
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

                alojamiento = new Alojamiento(idAlojamiento, nombre, plazas, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, idPropietario, idUsuario);
                lista.add(alojamiento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return lista;
    }

    public Alojamiento encontrarPorId(int id) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alojamiento alojamiento = new Alojamiento();

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_POR_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idAlojamiento = rs.getInt(1);
                String nombre = rs.getString(2);
                int plazas = rs.getInt(3);
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

                alojamiento.setIdAlojamiento(idAlojamiento);
                alojamiento.setNombre(nombre);
                alojamiento.setPlazas(plazas);
                alojamiento.setDormitorios(dormitorios);
                alojamiento.setBanos(banos);
                alojamiento.setTerraza(terraza);
                alojamiento.setPiscina(piscina);
                alojamiento.setAparcamiento(aparcamiento);
                alojamiento.setDireccion(direccion);
                alojamiento.setPoblacion(poblacion);
                alojamiento.setProvincia(provincia);
                alojamiento.setIdPropietario(idPropietario);
                alojamiento.setIdUsuario(idUsuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return alojamiento;
    }

    public List encontrarPorIdPropietario(int id) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Alojamiento> lista = new ArrayList<>();
        Alojamiento alojamiento = null;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_SELECT_POR_ID_PROPIETARIO);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idAlojamiento = rs.getInt(1);
                String nombre = rs.getString(2);
                int plazas = rs.getInt(3);
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

                alojamiento = new Alojamiento(idAlojamiento, nombre, plazas, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, idPropietario, idUsuario);
                lista.add(alojamiento);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return lista;
    }*/
    // MODIFICADO SEGUN STANDARD SEGUIDO EN CLASE
    public int insertar(Alojamiento alojamiento) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        final String URL = "jdbc:mysql://localhost/tienda";
        final String USER = "root";
        final String PASSWORD = "";

        Connection cn = null;
        PreparedStatement ps = null;
        int elementosInsertados = 0;

        try {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
            ps = cn.prepareStatement(SQL_INSERT);
            ps.setString(1, alojamiento.getNombre());
            ps.setInt(2, alojamiento.getPlazas());
            ps.setInt(3, alojamiento.getDormitorios());
            ps.setInt(4, alojamiento.getBanos());
            ps.setInt(5, alojamiento.getTerraza());
            ps.setInt(6, alojamiento.getPiscina());
            ps.setInt(7, alojamiento.getAparcamiento());
            ps.setString(8, alojamiento.getDireccion());
            ps.setString(9, alojamiento.getPoblacion());
            ps.setString(10, alojamiento.getProvincia());
            ps.setInt(11, alojamiento.getIdPropietario());
            ps.setInt(12, alojamiento.getIdUsuario());

            elementosInsertados = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ps.close();
            cn.close();
        }

        return elementosInsertados;
    }
    /*
    public int actualizar(Alojamiento alojamiento) {
        Connection cn = null;
        PreparedStatement ps = null;
        int elementosActualizados = 0;

        try {
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_UPDATE);
            ps.setString(1, alojamiento.getNombre());
            ps.setInt(2, alojamiento.getPlazas());
            ps.setInt(3, alojamiento.getDormitorios());
            ps.setInt(4, alojamiento.getBanos());
            ps.setString(5, alojamiento.getTerraza());
            ps.setString(6, alojamiento.getPiscina());
            ps.setString(7, alojamiento.getAparcamiento());
            ps.setString(8, alojamiento.getDireccion());
            ps.setString(9, alojamiento.getPoblacion());
            ps.setString(10, alojamiento.getProvincia());
            ps.setInt(11, alojamiento.getIdPropietario());
            ps.setInt(12, alojamiento.getIdUsuario());
            ps.setInt(13, alojamiento.getIdAlojamiento());

            elementosActualizados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            cn = Conexion.getConnection();
            ps = cn.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);

            eliminado = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlojamientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return eliminado;
    }*/

}
