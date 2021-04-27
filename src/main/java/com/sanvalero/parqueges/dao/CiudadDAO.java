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

    /*
CuentaCorriente = Parque  ParqueDAO parqueDAO parque
Usuario =  Ciudad    CiudadDAO  ciudadDAO ciudad
     */

    public int verId(String nombre) throws SQLException {
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

        }
        return ciudad1;

    }
}
