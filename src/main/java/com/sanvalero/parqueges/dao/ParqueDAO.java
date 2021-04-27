package com.sanvalero.parqueges.dao;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
import com.sanvalero.parqueges.domain.Parque;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParqueDAO {

    private Conexion connection;
    private CiudadDAO ciudadDAO;

    public ParqueDAO(Conexion connection) {
        this.connection = connection;
    }

    public void crearParque(Parque parque) throws SQLException {
        String sql = "INSERT INTO PARQUES (id_ciudad, extension, nombre) VALUES (?, ?, ?)";

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setInt(1, parque.getId_ciudad());
        sentencia.setInt(2, parque.getExtension());
        sentencia.setString(3, parque.getNombre());

        sentencia.executeUpdate();
    }

    public ArrayList<Parque> listarParques(int id_ciudad) throws SQLException {
        String sql = "SELECT nombre FROM parques WHERE id_ciudad = ?";

        PreparedStatement sentencia;
        sentencia = connection.getConexion().prepareStatement(sql);
        ArrayList<Parque> park1 = new ArrayList<>();
        sentencia.setInt(1, id_ciudad);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setNombre(resultado.getString(1));

            park1.add(parque);
        }
        return park1;
    }
    public int verIdParque(String nombre) throws SQLException {
        String sql = "SELECT id_parque FROM parques WHERE nombre = ?";
        int id_parque = 0;
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            id_parque = resultado.getInt("id_parque");
        }
        return id_parque;
    }

    public void modificarParque(String nombre, int id_parque, int extension) throws SQLException {
        String sql = "UPDATE parques SET nombre = ?, extension = ?  WHERE id_parque = ?";
        id_parque = verIdParque(nombre);
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, nombre);
        sentencia.setInt(2, extension);
        sentencia.setInt(3, id_parque);
        sentencia.executeUpdate();
        
        
//public void modificarParque(String nuevonombre, int id_parque, int nuevaextension)
//  sentencia.setString(1, nuevonombre);
//        sentencia.setInt(2, nuevaextension);
//        float saldo = cc.getSaldo() + util.getIngreso();
//        sentencia.setInt(2, cc.getId_usuario());
//        sentencia.setInt(2, id_usuario);
//        sentencia.setFloat(1, saldo);
//        sentencia.executeUpdate();
    }

    public ArrayList<Parque> buscarParque(String cadenaBusqueda) throws SQLException {
        String sql = "SELECT * FROM parques WHERE nombre = ?";
        ArrayList<Parque> parques = new ArrayList<>();

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, cadenaBusqueda);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Parque parque = new Parque();
            parque.setNombre(resultado.getString(1));
            

            parques.add(parque);
        }

        return parques;
    }

    public int numParquesExt(int id_ciudad, int extension) throws SQLException {
        String sql = "SELECT count(*) FROM parques WHERE id_ciudad = ? AND extension >= ?;";   
        int cantidad1 = 0; 
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setInt(1, id_ciudad);
        sentencia.setInt(2, extension);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()){
        cantidad1 = resultado.getInt("cantidad1");
        }        
        return cantidad1;
    }
    public void borrarParquesDeCiudad(int id_ciudad) throws SQLException {
        String sql = "DELETE FROM parques WHERE id_ciudad = ?)";

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setInt(1, id_ciudad);
        sentencia.executeUpdate();
    }
}
