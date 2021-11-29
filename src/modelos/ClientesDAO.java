package modelos;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClientesDAO {
    
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
    //(String nombre, String apellidos, String nif, String telefono, String email, int tipo, double presupuestoDesde, double presupuestoHasta, int dormitoriosDesde, int dormitoriosHasta, int terraza, int aparcamiento, int piscina, int ascensor, String poblacion, String provincia, int idUsuario)

}
