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

    private final String SQL_SELECT = "SELECT id_cliente, nombre, apellidos, nif, telefono, email, tipo, presupuesto_min, presupuesto_max, dormitorios_min, dormitorios_max, terraza_balcon, aparcamiento, piscina, ascensor, poblacion, provincia, id_usuario FROM clientes";

    private final String SQL_SELECT_ID = "SELECT id_cliente, nombre, apellidos, nif, telefono, email, tipo, presupuesto_min, presupuesto_max, dormitorios_min, dormitorios_max, terraza_balcon, aparcamiento, piscina, ascensor, poblacion, provincia, id_usuario FROM clientes WHERE id_cliente = ?";

    private final String SQL_INSERT = "INSERT INTO clientes VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String SQL_UPDATE = "UPDATE clientes SET nombre=?, apellidos=?, nif=?, telefono=?, email=?, tipo=?, presupuesto_min=?, presupuesto_max=?, dormitorios_min=?, dormitorios_max=?, terraza_balcon=?, aparcamiento=?, piscina=?, ascensor=?, poblacion=?, provincia=?, id_usuario=? WHERE id_cliente=?";

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
                String tipo = rs.getString(7);
                int presupuestoMin = rs.getInt(8);
                int presupuestoMax = rs.getInt(9);
                int dormitoriosMin = rs.getInt(10);
                int dormitoriosMax = rs.getInt(11);
                int terrazaBalcon = rs.getInt(12);
                int aparcamiento = rs.getInt(13);
                int piscina = rs.getInt(14);
                int ascensor = rs.getInt(15);
                String poblacion = rs.getString(16);
                String provincia = rs.getString(17);
                int idUsuario = rs.getInt(18);

                cliente = new Clientes(idCliente, nombre, apellidos, nif, telefono, email, tipo, presupuestoMin, presupuestoMax, dormitoriosMin,
                        dormitoriosMax, terrazaBalcon, aparcamiento, piscina, ascensor, poblacion, provincia, idUsuario);
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
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Clientes cliente = new Clientes();

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, clienteSeleccionado);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idCliente = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellidos = rs.getString(3);;
                String nif = rs.getString(4);
                String telefono = rs.getString(5);
                String email = rs.getString(6);
                String tipo = rs.getString(7);
                int presupuestoMin = rs.getInt(8);
                int presupuestoMax = rs.getInt(9);
                int dormitoriosMin = rs.getInt(10);
                int dormitoriosMax = rs.getInt(11);
                int terrazaBalcon = rs.getInt(12);
                int aparcamiento = rs.getInt(13);
                int piscina = rs.getInt(14);
                int ascensor = rs.getInt(15);
                String poblacion = rs.getString(16);
                String provincia = rs.getString(17);
                int idUsuario = rs.getInt(18);

                cliente = new Clientes(idCliente, nombre, apellidos, nif, telefono, email, tipo, presupuestoMin, presupuestoMax, dormitoriosMin,
                        dormitoriosMax, terrazaBalcon, aparcamiento, piscina, ascensor, poblacion, provincia, idUsuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return cliente;
    }

    public int insertar(Clientes cliente) {
        Connection cn = null;
        PreparedStatement ps = null;
        int elementosInsertados = 0;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_INSERT);

            ps.setInt(1, 0);
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getNif());
            ps.setString(5, cliente.getTelefono());
            ps.setString(6, cliente.getEmail());
            ps.setString(7, cliente.getTipo());
            ps.setInt(8, cliente.getPresupuestoMin());
            ps.setInt(9, cliente.getPresupuestoMax());
            ps.setInt(10, cliente.getDormitoriosMin());
            ps.setInt(11, cliente.getDormitoriosMax());
            ps.setInt(12, cliente.getTerrazaBalcon());
            ps.setInt(13, cliente.getAparcamiento());
            ps.setInt(14, cliente.getPiscina());
            ps.setInt(15, cliente.getAscensor());
            ps.setString(16, cliente.getPoblacion());
            ps.setString(17, cliente.getProvincia());
            ps.setInt(18, cliente.getIdUsuario());

            elementosInsertados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return elementosInsertados;
    }

    public int actualizar(Clientes clienteSeleccionado) {
        Connection cn = null;
        PreparedStatement ps = null;
        int elementosActualizados = 0;

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_UPDATE);
            ps.setString(1, clienteSeleccionado.getNombre());
            ps.setString(2, clienteSeleccionado.getApellidos());
            ps.setString(3, clienteSeleccionado.getNif());
            ps.setString(4, clienteSeleccionado.getTelefono());
            ps.setString(5, clienteSeleccionado.getEmail());
            ps.setString(6, clienteSeleccionado.getTipo());
            ps.setInt(7, clienteSeleccionado.getPresupuestoMin());
            ps.setInt(8, clienteSeleccionado.getPresupuestoMax());
            ps.setInt(9, clienteSeleccionado.getDormitoriosMin());
            ps.setInt(10, clienteSeleccionado.getDormitoriosMax());
            ps.setInt(11, clienteSeleccionado.getTerrazaBalcon());
            ps.setInt(12, clienteSeleccionado.getAparcamiento());
            ps.setInt(13, clienteSeleccionado.getPiscina());
            ps.setInt(14, clienteSeleccionado.getAscensor());
            ps.setString(15, clienteSeleccionado.getPoblacion());
            ps.setString(16, clienteSeleccionado.getProvincia());
            ps.setInt(17, clienteSeleccionado.getIdUsuario());
            //Where:
            ps.setInt(18, clienteSeleccionado.getIdCliente());

            elementosActualizados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return eliminado;
    }
}
