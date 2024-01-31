package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.modelo.Idioma;
import org.iesvdm.pruebaud3.modelo.Pelicula;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilDAO {

    public static Pelicula newPelicula(ResultSet rs) throws SQLException {
        return new Pelicula(rs.getInt("id_pelicula"),
                rs.getString("titulo"),
//                rs.getString("descripcion"),
                rs.getDate("fecha_lanzamiento"),
                new Idioma(rs.getInt("I.id_idioma"),
                        rs.getString("I.nombre"),
                        rs.getDate("I.ultima_actualizacion")
                ),
                rs.getInt("duracion_alquiler"),
                rs.getBigDecimal("rental_rate"),
                rs.getInt("duracion"),
                rs.getBigDecimal("replacement_cost")
//                rs.getDate("ultima_actualizacion")
        );
    }
}
