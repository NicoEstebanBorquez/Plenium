package modelos;

import gestionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClientesDAO {

    final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente = ?";

    public List listar() {
        List<Clientes> lista = null;
        SessionFactory sf = null;
        Session sesion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();

            Query query = sesion.createQuery("FROM Clientes");
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
            return lista;
        }
    }

    public Clientes obtenerPorId(int clienteSeleccionado) {
        Clientes cliente = null;
        SessionFactory sf = null;
        Session sesion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            Query consulta = sesion.createQuery("FROM Clientes WHERE idCliente = " + clienteSeleccionado);
            List<Clientes> lista = consulta.list();
            Iterator iterador = lista.iterator();
            while (iterador.hasNext()) {
                cliente = (Clientes) iterador.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
            return cliente;
        }
    }

    public int insertar(Clientes cliente) {

        int confirmacion = 0;
        SessionFactory sf = null;
        Session sesion = null;
        Transaction transaccion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            transaccion = sesion.beginTransaction();

            sesion.save(cliente);
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

    public int actualizar(Clientes clienteSeleccionado) {
        int confirmacion = 0;
        Clientes clienteActualizar = null;
        SessionFactory sf = null;
        Session sesion = null;
        Transaction transaccion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            transaccion = sesion.beginTransaction();

            //Se hace una consulta a la BD con "obtenerPorId", con lo que se obtiene de la BD
            //el cliente que queremos modificar
            clienteActualizar = this.obtenerPorId(clienteSeleccionado.getIdCliente());
            // Ahora se mofican los campos:
            clienteActualizar.setNombre(clienteSeleccionado.getNombre());
            clienteActualizar.setApellidos(clienteSeleccionado.getApellidos());
            clienteActualizar.setNif(clienteSeleccionado.getNif());
            clienteActualizar.setTelefono(clienteSeleccionado.getTelefono());
            clienteActualizar.setEmail(clienteSeleccionado.getEmail());
            clienteActualizar.setIdUsuario(clienteSeleccionado.getIdUsuario());
            clienteActualizar.setTipo(clienteSeleccionado.getTipo());
            clienteActualizar.setPresupuestoMin(clienteSeleccionado.getPresupuestoMin());
            clienteActualizar.setPresupuestoMax(clienteSeleccionado.getPresupuestoMax());
            clienteActualizar.setDormitoriosMin(clienteSeleccionado.getDormitoriosMin());
            clienteActualizar.setDormitoriosMax(clienteSeleccionado.getDormitoriosMax());
            clienteActualizar.setTerrazaBalcon(clienteSeleccionado.getTerrazaBalcon());
            clienteActualizar.setAparcamiento(clienteSeleccionado.getAparcamiento());
            clienteActualizar.setPiscina(clienteSeleccionado.getPiscina());
            clienteActualizar.setAscensor(clienteSeleccionado.getAscensor());
            clienteActualizar.setPoblacion(clienteSeleccionado.getPoblacion());
            clienteActualizar.setProvincia(clienteSeleccionado.getProvincia());

            //Se hace el update
            sesion.update(clienteActualizar);
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
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return eliminado;
    }
}
