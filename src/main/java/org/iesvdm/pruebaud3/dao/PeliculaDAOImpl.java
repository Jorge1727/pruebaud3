package org.iesvdm.pruebaud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

//Anotación lombok para logging (traza) de la aplicación
@Slf4j
//Un Repository es un componente y a su vez un estereotipo de Spring 
//que forma parte de la ‘capa de persistencia’.
@Repository
public class PeliculaDAOImpl implements PeliculaDAO {

	 //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
	/**
	 * Inserta en base de datos el nuevo Pelicula, actualizando el id en el bean Pelicula.
	 */
	@Override
	public synchronized void create(Pelicula pelicula) {

		//Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
		String sqlInsert = """
							INSERT INTO pelicula (titulo, fecha_lanzamiento, id_idioma, duracion_alquiler, rental_rate, duracion, replacement_cost) 
							VALUES  (     ?,         ?,         ?,       ?,?,?)
						   """;

		KeyHolder keyHolder = new GeneratedKeyHolder();
		//Con recuperación de id generado
		int rows = jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
			int idx = 1;
			ps.setString(idx++, pelicula.getTitulo());

			java.sql.Date fechaSql = new java.sql.Date(pelicula.getFecha_lanzamiento().getTime());
			ps.setDate(idx++, fechaSql);

			ps.setInt(idx++, pelicula.getIdioma().getId_idioma());
			ps.setInt(idx++, pelicula.getDuracion_alquiler());
			ps.setBigDecimal(idx++, pelicula.getRental_rate());
			ps.setInt(idx++, pelicula.getDuracion());
			ps.setBigDecimal(idx++, pelicula.getReplacement_cost());

			return ps;
		},keyHolder);

		pelicula.setId_pelicula(keyHolder.getKey().intValue());

		//Sin recuperación de id generado
//		int rows = jdbcTemplate.update(sqlInsert,
//							pelicula.getNombre(),
//							pelicula.getApellido1(),
//							pelicula.getApellido2(),
//							pelicula.getCiudad(),
//							pelicula.getCategoria()
//					);

		log.info("Insertados {} registros.", rows);
	}

	/**
	 * Devuelve lista con todos loa Peliculas.
	 */
	@Override
	public List<Pelicula> getAll() {

		List<Pelicula> listPelicula = this.jdbcTemplate.query("""
                SELECT * FROM  pelicula P left join idioma I on  P.id_idioma = I.id_idioma
                                        
                """, (rs, rowNum) -> UtilDAO.newPelicula(rs)
		);
		
		log.info("Devueltos {} registros.", listPelicula.size());
		
        return listPelicula;
        
	}


//	/**
//	 * Devuelve Optional de Pelicula con el ID dado.
//	 */
//	@Override
//	public Optional<Pelicula> find(int id) {
//
//		Pelicula fab =  jdbcTemplate
//				.queryForObject("SELECT * FROM pelicula WHERE id = ?"
//								, (rs, rowNum) -> new Pelicula(rs.getInt("id"),
//            						 						rs.getString("nombre"),
//            						 						rs.getString("apellido1"),
//            						 						rs.getString("apellido2"),
//            						 						rs.getString("ciudad"),
//            						 						rs.getInt("categoría"),
//															rs.getString("email"))
//								, id
//								);
//
//		if (fab != null) {
//			return Optional.of(fab);}
//		else {
//			log.info("Pelicula no encontrado.");
//			return Optional.empty(); }
//
//	}
//	/**
//	 * Actualiza Pelicula con campos del bean Pelicula según ID del mismo.
//	 */
//	@Override
//	public void update(Pelicula pelicula) {
//
//		int rows = jdbcTemplate.update("""
//										UPDATE pelicula SET
//														nombre = ?,
//														apellido1 = ?,
//														apellido2 = ?,
//														ciudad = ?,
//														categoría = ?,
//														email = ?
//												WHERE id = ?
//										""", pelicula.getNombre()
//										, pelicula.getApellido1()
//										, pelicula.getApellido2()
//										, pelicula.getCiudad()
//										, pelicula.getCategoria()
//										, pelicula.getEmail()
//										, pelicula.getId());
//
//		log.info("Update de Pelicula con {} registros actualizados.", rows);
//
//	}
//
//	/**
//	 * Borra Pelicula con ID proporcionado.
//	 */
//	@Override
//	public void delete(long id) {
//
//		int rows = jdbcTemplate.update("DELETE FROM pelicula WHERE id = ?", id);
//
//		log.info("Delete de Pelicula con {} registros eliminados.", rows);
//
//	}
//
}
