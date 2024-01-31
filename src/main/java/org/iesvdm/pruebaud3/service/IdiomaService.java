package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.IdiomaDAO;
import org.iesvdm.pruebaud3.modelo.Idioma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdiomaService {

	@Autowired
	private IdiomaDAO idiomaDAO;

	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired

	public List<Idioma> listAll() {
		return idiomaDAO.getAll();
	}


//
//	public Idioma one(Integer id) {
//		Optional<Idioma> optIdioma = idiomaDAO.find(id);
//		if (optIdioma.isPresent())
//			return optIdioma.get();
//		else
//			return null;
//	}
//
//	public void newIdioma(Idioma cliente) {
//
//		idiomaDAO.create(cliente);
//	}
//
//	public void replaceIdioma(Idioma cliente) {
//
//		idiomaDAO.update(cliente);
//	}
//
//	public void deleteIdioma(int id){
//		idiomaDAO.delete(id);
//	}
//

}
