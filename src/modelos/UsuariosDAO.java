package modelos;

import gestionBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDAO {

    private final String SQL_SELECT = "SELECT * FROM usuarios";

    private final String SQL_SELECT_ID = "SELECT * FROM usuarios WHERE id_usuario=?";

    private final String SQL_INSERT = "INSERT INTO usuarios VALUES (?,?,?,?,?,?)";

    private final String SQL_UPDATE = "UPDATE usuarios SET nombre=?, apellidos=?, telefono=?, email=? WHERE id_usuario=?";

    final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuario=?";
    
    
    
    public Usuarios obtenerPorId(int usuarioSeleccionado) {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuarios usuario = new Usuarios();

        try {
            cn = Conexion.abrirConexion();
            ps = cn.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, usuarioSeleccionado);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellidos = rs.getString(3);;
                String telefono = rs.getString(4);
                String email = rs.getString(5);
                int idOficina = rs.getInt(6);

                usuario = new Usuarios(idUsuario, nombre, apellidos, telefono, email, idOficina);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(ps);
            Conexion.close(cn);
        }
        return usuario;
    }
}
