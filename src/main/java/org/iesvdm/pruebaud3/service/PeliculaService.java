package org.iesvdm.pruebaud3.service;

import org.iesvdm.pruebaud3.dao.PeliculaDAO;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaService {

	@Autowired
	private PeliculaDAO peliculaDAO;

	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired

	public List<Pelicula> listAll() {
		return peliculaDAO.getAll();
	}

//	public List<Map.Entry<Cliente, Double>> listadoOrden(){
//		List<Pelicula> listaPeliculas = peliculaDAO.getAll();
//
//		Map<Cliente, Double> sumaPorCliente = listaPeliculas.stream()
//				.collect(Collectors.groupingBy(Pelicula::getCliente, Collectors.summingDouble(Pelicula::getTotal)));
//
//		List<Map.Entry<Cliente, Double>> clientesOrdenadosPorSuma = sumaPorCliente.entrySet().stream()
//				.sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
//				.collect(Collectors.toList());
//
//		return clientesOrdenadosPorSuma;
//	}
//
//	public Pelicula one(Integer id) {
//		Optional<Pelicula> optPelicula = peliculaDAO.find(id);
//		if (optPelicula.isPresent())
//			return optPelicula.get();
//		else
//			return null;
//	}
//
	public void newPelicula(Pelicula pelicula) {

		peliculaDAO.create(pelicula);
	}

//	// Para crear mediante busqueda ya que no sabia la existencia del mapper y dto
//	public void newPeliculaIds(Pelicula pelicula, Integer id_cliente, Integer id_comercial) {
//
//		peliculaDAO.createCliCom(pelicula, id_cliente, id_comercial);
//	}
//
//	public void replacePelicula(Pelicula pelicula) {
//
//		peliculaDAO.update(pelicula);
//	}
//
//	public void replacePeliculaIds(Pelicula pelicula, Integer id_cliente, Integer id_comercial) {
//
//		peliculaDAO.updateCliCom(pelicula, id_cliente, id_comercial);
//	}
//
//	public void deletePelicula(int id){
//		peliculaDAO.delete(id);
//	}
}
