package org.iesvdm.pruebaud3.dao;

import org.iesvdm.pruebaud3.modelo.Idioma;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IdiomaDAO {
//
//	public void create(Cliente cliente);
//
	public List<Idioma> getAll();
//	public Optional<Cliente>  find(int id);
//
//	public void update(Cliente cliente);
//
//	public void delete(long id);

}
