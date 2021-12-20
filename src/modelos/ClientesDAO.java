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

public class ClientesDAO {

    private final String SQL_SELECT = "SELECT id_cliente, nombre, apellidos, nif, telefono, email, tipo, presupuesto_min, presupuesto_max, dormitorios_min, "
            + " dormitorios_max, terraza_balcon, aparcamiento, piscina, ascensor, poblacion, provincia, id_usuario FROM clientes";

    final String SQL_DELETE = "DELETE FROM clientes WHERE id_cliente = ?";

    public List listar() {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Clientes> lista = new ArrayList<>();

        Clientes cliente = null;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellidos = rs.getString(3);;
                String nif = rs.getString(4);
                String telefono = rs.getString(5);
                String email = rs.getString(6);
                int tipo = rs.getInt(7);
                double presupuestoMin = rs.getDouble(8);
                double presupuestoMax = rs.getDouble(9);
                int dormitoriosMin = rs.getInt(10);
                int dormitoriosMax = rs.getInt(11);
                int terrazaBalcon = rs.getInt(12);
                int aparcamiento = rs.getInt(13);
                int piscina = rs.getInt(14);
                int ascensor = rs.getInt(15);
                String poblacion = rs.getString(16);
                String provincia = rs.getString(17);
                int idUsuario = rs.getInt(18);

                

                cliente = new Clientes(nombre, apellidos, nif, telefono, email, tipo, presupuestoMin, presupuestoMax, dormitoriosMin, dormitoriosMax, terrazaBalcon, aparcamiento, piscina, ascensor, poblacion, provincia, idUsuario);
                lista.add(cliente);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return lista;

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
