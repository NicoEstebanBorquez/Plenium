package modelos;

import gestionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OficinasDAO {

    private final String SQL_SELECT = "SELECT * FROM oficinas";

    private final String SQL_SELECT_ID = "SELECT * FROM oficinas WHERE id_oficina=?";

    private final String SQL_INSERT = "INSERT INTO oficinas VALUES (?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE oficinas SET nombre=?, telefono=?, email=? WHERE id_oficina=?";

    final String SQL_DELETE = "DELETE FROM oficinas WHERE id_oficina=?";

    public Oficinas obtenerPorId(int oficinaSeleccionado) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Oficinas oficina = new Oficinas();

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, oficinaSeleccionado);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idOficina = rs.getInt(1);
                String nombre = rs.getString(2);
                String telefono = rs.getString(3);
                String email = rs.getString(4);

                oficina = new Oficinas(idOficina, nombre, telefono, email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OficinasDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return oficina;
    }
}
