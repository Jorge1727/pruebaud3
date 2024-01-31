package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaDAO {

	public void create(Pelicula pelicula);

	public List<Pelicula> getAll();
//	public Optional<Pelicula>  find(int id);
//
//	public void update(Pelicula pelicula);
//
//	public void delete(long id);

}
