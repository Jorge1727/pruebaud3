package org.iesvdm.pruebaud3.dao;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.pruebaud3.modelo.Idioma;
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
public class IdiomaDAOImpl implements IdiomaDAO {

	 //Plantilla jdbc inyectada automáticamente por el framework Spring, gracias a la anotación @Autowired.
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	
//	/**
//	 * Inserta en base de datos el nuevo Idioma, actualizando el id en el bean Idioma.
//	 */
//	@Override
//	public synchronized void create(Idioma idioma) {
//
//							//Desde java15+ se tiene la triple quote """ para bloques de texto como cadenas.
//		String sqlInsert = """
//							INSERT INTO idioma (nombre, apellido1, apellido2, ciudad, categoría, email)
//							VALUES  (     ?,         ?,         ?,       ?,         ?,	?)
//						   """;
//
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		//Con recuperación de id generado
//		int rows = jdbcTemplate.update(connection -> {
//			PreparedStatement ps = connection.prepareStatement(sqlInsert, new String[] { "id" });
//			int idx = 1;
//			ps.setString(idx++, idioma.getNombre());
//			ps.setString(idx++, idioma.getApellido1());
//			ps.setString(idx++, idioma.getApellido2());
//			ps.setString(idx++, idioma.getCiudad());
//			ps.setInt(idx++, idioma.getCategoria());
//			ps.setString(idx, idioma.getEmail());
//			return ps;
//		},keyHolder);
//
//		idioma.setId(keyHolder.getKey().intValue());
//
//		//Sin recuperación de id generado
////		int rows = jdbcTemplate.update(sqlInsert,
////							idioma.getNombre(),
////							idioma.getApellido1(),
////							idioma.getApellido2(),
////							idioma.getCiudad(),
////							idioma.getCategoria()
////					);
//
//		log.info("Insertados {} registros.", rows);
//	}

	/**
	 * Devuelve lista con todos loa Idiomas.
	 */
	@Override
	public List<Idioma> getAll() {
		
		List<Idioma> listFab = jdbcTemplate.query(
                "SELECT * FROM idioma",
                (rs, rowNum) -> new Idioma(rs.getInt("id_idioma"),
                						 	rs.getString("nombre"),
                						 	rs.getDate("ultima_actualizacion")
                						 	)
        );
		
		log.info("Devueltos {} registros.", listFab.size());
		
        return listFab;
        
	}

//
//	/**
//	 * Devuelve Optional de Idioma con el ID dado.
//	 */
//	@Override
//	public Optional<Idioma> find(int id) {
//
//		Idioma fab =  jdbcTemplate
//				.queryForObject("SELECT * FROM idioma WHERE id = ?"
//								, (rs, rowNum) -> new Idioma(rs.getInt("id"),
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
//			log.info("Idioma no encontrado.");
//			return Optional.empty(); }
//
//	}
//	/**
//	 * Actualiza Idioma con campos del bean Idioma según ID del mismo.
//	 */
//	@Override
//	public void update(Idioma idioma) {
//
//		int rows = jdbcTemplate.update("""
//										UPDATE idioma SET
//														nombre = ?,
//														apellido1 = ?,
//														apellido2 = ?,
//														ciudad = ?,
//														categoría = ?,
//														email = ?
//												WHERE id = ?
//										""", idioma.getNombre()
//										, idioma.getApellido1()
//										, idioma.getApellido2()
//										, idioma.getCiudad()
//										, idioma.getCategoria()
//										, idioma.getEmail()
//										, idioma.getId());
//
//		log.info("Update de Idioma con {} registros actualizados.", rows);
//
//	}
//
//	/**
//	 * Borra Idioma con ID proporcionado.
//	 */
//	@Override
//	public void delete(long id) {
//
//		int rows = jdbcTemplate.update("DELETE FROM idioma WHERE id = ?", id);
//
//		log.info("Delete de Idioma con {} registros eliminados.", rows);
//
//	}
	
}
