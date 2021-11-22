package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.*;

public class AlojamientoDAO {

   /* private static final String SQL_SELECT = "SELECT id_alojamiento, nombre, capacidad, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario FROM alojamiento";
    private static final String SQL_SELECT_POR_ID = "SELECT id_alojamiento, nombre, capacidad, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario FROM alojamiento WHERE id_alojamiento=?";
    private static final String SQL_SELECT_POR_ID_PROPIETARIO = "SELECT id_alojamiento, nombre, capacidad, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario FROM alojamiento WHERE id_propietario=?";
    private static final String SQL_INSERT = "INSERT INTO alojamiento (nombre, capacidad, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, id_propietario, id_usuario) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE alojamiento SET nombre=?, capacidad=?, dormitorios=?, banos=?, terraza=?, piscina=?, aparcamiento=?, direccion=?, poblacion=?, provincia=?, id_propietario=?, id_usuario=? WHERE id_alojamiento=?";
    private static final String SQL_DELETE = "DELETE FROM alojamiento WHERE id_alojamiento=?";
*/
    public List listar() {
        List<Alojamiento> lista = null;
        SessionFactory sf = null;
        Session sesion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();

            Query query = sesion.createQuery("FROM Alojamiento");
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
            return lista;
        }
    }

    public Alojamiento obtenerPorId(int alojamientoSeleccionado) {
        Alojamiento alojamiento = null;
        SessionFactory sf = null;
        Session sesion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            Query consulta = sesion.createQuery("FROM Alojamiento WHERE idAlojamiento = " + alojamientoSeleccionado);
            List<Alojamiento> lista = consulta.list();
            Iterator iterador = lista.iterator();
            while (iterador.hasNext()) {
                alojamiento = (Alojamiento) iterador.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
            return alojamiento;
        }
    }

    public int insertar(Alojamiento alojamiento) {

        int confirmacion = 0;
        SessionFactory sf = null;
        Session sesion = null;
        Transaction transaccion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            transaccion = sesion.beginTransaction();

            sesion.save(alojamiento);
            transaccion.commit();
            confirmacion = 1;
        } catch (Exception e) {
            transaccion.rollback();
            e.printStackTrace();
        } finally {
            sesion.close();
            return confirmacion;
        }
    }

    public int actualizar(Alojamiento alojamientoSeleccionado) {
        int confirmacion = 0;
        Alojamiento alojamientoActualizar = null;
        SessionFactory sf = null;
        Session sesion = null;
        Transaction transaccion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            transaccion = sesion.beginTransaction();

            //Se hace una consulta a la BD con "obtenerPorId", con lo que se obtiene de la BD
            //el alojamiento que queremos modificar
            alojamientoActualizar = this.obtenerPorId(alojamientoSeleccionado.getIdAlojamiento());
            // Ahora se mofican los campos:
            alojamientoActualizar.setNombre(alojamientoSeleccionado.getNombre());
            alojamientoActualizar.setCapacidad(alojamientoSeleccionado.getCapacidad());
            alojamientoActualizar.setDormitorios(alojamientoSeleccionado.getDormitorios());
            alojamientoActualizar.setBanos(alojamientoSeleccionado.getBanos());
            alojamientoActualizar.setTerraza(alojamientoSeleccionado.getTerraza());
            alojamientoActualizar.setPiscina(alojamientoSeleccionado.getPiscina());
            alojamientoActualizar.setAparcamiento(alojamientoSeleccionado.getAparcamiento());
            alojamientoActualizar.setDireccion(alojamientoSeleccionado.getDireccion());
            alojamientoActualizar.setPoblacion(alojamientoSeleccionado.getPoblacion());
            alojamientoActualizar.setProvincia(alojamientoSeleccionado.getProvincia());
            alojamientoActualizar.setIdPropietario(alojamientoSeleccionado.getIdPropietario());
            alojamientoActualizar.setIdUsuario(alojamientoSeleccionado.getIdUsuario());
            //Se hace el update
            sesion.update(alojamientoActualizar);
            transaccion.commit();
            confirmacion = 1;
        } catch (Exception e) {
            transaccion.rollback();
            e.printStackTrace();
        } finally {
            sesion.close();
            return confirmacion;
        }
    }

    /*
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

                alojamiento = new Alojamiento(idAlojamiento, nombre, capacidad, dormitorios, banos, terraza, piscina, aparcamiento, direccion, poblacion, provincia, idPropietario, idUsuario);
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
 /*
   
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
