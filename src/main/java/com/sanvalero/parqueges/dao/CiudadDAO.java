package com.sanvalero.parqueges.dao;

import com.sanvalero.parqueges.domain.Ciudad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */
public class CiudadDAO {

    private Conexion connection;

    public CiudadDAO(Conexion connection) {
        this.connection = connection;
    }

    public int verIdCiudad(String nombre) throws SQLException {
        String sql = "SELECT id_ciudad FROM ciudades WHERE nombre = ?";
        int id_ciudad = 0;
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            id_ciudad = resultado.getInt("id_ciudad");
        }
        return id_ciudad;
    }

//    nombresC = ciudadDAO.nombresCiudad(ccaa);
    public ArrayList<Ciudad> nombresCiudad(String ccaa) throws SQLException {
        String sql = "SELECT nombre FROM ciudades WHERE ccaa = ?";
        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        ArrayList<Ciudad> nombresC = new ArrayList<>();
        sentencia.setString(1, ccaa);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Ciudad ciudad = new Ciudad();
            ciudad.setNombre(resultado.getString(1));
            nombresC.add(ciudad);

        }
        return nombresC;
    }

    public ArrayList<Ciudad> listarCiudadesParquesExtension(int extension) throws SQLException {
        String sql = "SELECT c.nombre, SUM(p.extension) \n"
                + "FROM ciudades c inner JOIN parques p \n"
                + "ON c.id_ciudad=p.id_ciudad \n"
                + "GROUP BY c.nombre \n"
                + "HAVING SUM(p.extension) >= ?";

        PreparedStatement sentencia = connection.getConexion().prepareStatement(sql);
        ArrayList<Ciudad> ciudad1 = new ArrayList<>();
        sentencia.setInt(2, extension);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Ciudad ciudad = new Ciudad();
            ciudad.setNombre(resultado.getString(1));
            ciudad1.add(ciudad);
        }
        return ciudad1;

    }
}
