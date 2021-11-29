//idInmueble
package modelos;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class InmueblesDAO {

    public List listar() {
        List<Inmuebles> lista = null;
        SessionFactory sf = null;
        Session sesion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();

            Query query = sesion.createQuery("FROM Inmuebles");
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
            return lista;
        }
    }

    public Inmuebles obtenerPorId(int inmuebleSeleccionado) {
        Inmuebles inmueble = null;
        SessionFactory sf = null;
        Session sesion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            Query consulta = sesion.createQuery("FROM Inmuebles WHERE idInmueble = " + inmuebleSeleccionado);
            List<Inmuebles> lista = consulta.list();
            Iterator iterador = lista.iterator();
            while (iterador.hasNext()) {
                inmueble = (Inmuebles) iterador.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sesion.close();
            return inmueble;
        }
    }

    public int insertar(Inmuebles inmueble) {

        int confirmacion = 0;
        SessionFactory sf = null;
        Session sesion = null;
        Transaction transaccion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            transaccion = sesion.beginTransaction();

            sesion.save(inmueble);
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

    public int actualizar(Inmuebles inmuebleSeleccionado) {
        int confirmacion = 0;
        Inmuebles inmuebleActualizar = null;
        SessionFactory sf = null;
        Session sesion = null;
        Transaction transaccion = null;
        try {
            sf = SessionFactorySingleton.getSessionFactory();
            sesion = sf.openSession();
            transaccion = sesion.beginTransaction();

            //Se hace una consulta a la BD con "obtenerPorId", con lo que se obtiene de la BD
            //el inmueble que queremos modificar
            inmuebleActualizar = this.obtenerPorId(inmuebleSeleccionado.getIdInmueble());
            // Ahora se mofican los campos:
            inmuebleActualizar.setNombre(inmuebleSeleccionado.getNombre());
            inmuebleActualizar.setDormitorios(inmuebleSeleccionado.getDormitorios());
            inmuebleActualizar.setBanos(inmuebleSeleccionado.getBanos());
            inmuebleActualizar.setTerraza(inmuebleSeleccionado.getTerraza());
            inmuebleActualizar.setPiscina(inmuebleSeleccionado.getPiscina());
            inmuebleActualizar.setAparcamiento(inmuebleSeleccionado.getAparcamiento());
            inmuebleActualizar.setDireccion(inmuebleSeleccionado.getDireccion());
            inmuebleActualizar.setPoblacion(inmuebleSeleccionado.getPoblacion());
            inmuebleActualizar.setProvincia(inmuebleSeleccionado.getProvincia());
            inmuebleActualizar.setIdPropietario(inmuebleSeleccionado.getIdPropietario());
            inmuebleActualizar.setIdUsuario(inmuebleSeleccionado.getIdUsuario());
            //Se hace el update
            sesion.update(inmuebleActualizar);
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
    
    //(String nombre, int tipo, double precio, double superficieConstruida, double superficieTotal, String direccion, String poblacion, String provincia, 
    //int categoria, Date fechaPublicacion, int dormitorios, int banos, int amueblado, int semiAmueblado, int noAmueblado, int cocinaEquipada, int aireAcondicionado, 
    //int calefaccionCentral, int gasNatural, int placasSolares, int armariosEmpotrados, int terraza, int balcon, int piscina, int aparcamiento, int ascensor, 
    //int trastero, int jardin, int idPropietario, int idUsuario)

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
            Logger.getLogger(InmueblesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return eliminado;
    }*/
}
