package org.iesvdm.pruebaud3.controlador;

import jakarta.validation.Valid;
import org.iesvdm.pruebaud3.dto.PeliculaDTO;
import org.iesvdm.pruebaud3.mapper.PeliculaMapper;
import org.iesvdm.pruebaud3.modelo.Idioma;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.iesvdm.pruebaud3.service.IdiomaService;
import org.iesvdm.pruebaud3.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	private PeliculaService peliculaService;
	@Autowired
	private IdiomaService idiomaService;
	@Autowired
	private PeliculaMapper peliculaMapper;
	//Se utiliza inyección automática por constructor del framework Spring.
	//Por tanto, se puede omitir la anotación Autowired

	//@RequestMapping(value = "/peliculas", method = RequestMethod.GET)
	//equivalente a la siguiente anotación
	@GetMapping("/peliculas") //Al no tener ruta base para el controlador, cada método tiene que tener la ruta completa
	public String listar(Model model) {
		
		List<Pelicula> listaPeliculas =  peliculaService.listAll();

		model.addAttribute("listaPeliculas", listaPeliculas);

		return "peliculas";// es el nombre de templates, lo redirigue all
	}

	@GetMapping("/peliculas/crear")
	public String crear(Model model) {

		PeliculaDTO peliculaDTO = new PeliculaDTO();
		model.addAttribute("peliculaDTO", peliculaDTO);

		List<Idioma> listaIdiomas = this.idiomaService.listAll();
		model.addAttribute("listaIdiomas", listaIdiomas);

		return "crear-pelicula";

	}
	@PostMapping("/peliculas/crear")
	public String submitCrear(@Valid @ModelAttribute("peliculaDTO") PeliculaDTO peliculaDTO, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("peliculaDTO", peliculaDTO);

			List<Idioma> listaIdiomas = this.idiomaService.listAll();
			model.addAttribute("listaIdiomas", listaIdiomas);

			return "crear-pelicula";
		}

		Pelicula pedido = peliculaMapper.peliculaFormDTOAPelicula(peliculaDTO);

		peliculaService.newPelicula(pedido);

		return "redirect:/peliculas";
	}

////	para crear mediante busqueda AHORA NO LE DOY USO--------------------------------------------------------vv
//	@GetMapping("/peliculas/crear/{id_cliente}/{id_comercial}")
//	public String crear(Model model, @PathVariable Integer id_cliente, @PathVariable Integer id_comercial) {
//
//		Pelicula pedido = new Pelicula();
//
//		model.addAttribute("id_cliente", id_cliente);
//		model.addAttribute("id_comercial", id_comercial);
//		model.addAttribute("pedido", pedido);
//
//		return "crear-pedido";
//	}
//
//	@PostMapping("/peliculas/crear/{clienteId}/{comercialId}")
//	public String submitCrear(@Valid @ModelAttribute("pedido") Pelicula pedido, Errors errors, @PathVariable String clienteId, @PathVariable String comercialId) {
//
//		Integer id_cliente = Integer.parseInt(clienteId);
//		Integer id_comercial = Integer.parseInt(comercialId);
//
//		if(errors.hasErrors()){
//			return "crear-pedido";
//		}
//
//		peliculaService.newPeliculaIds(pedido, id_cliente, id_comercial);
//
//		return "redirect:/peliculas";
//
//	}
//
//	@GetMapping("/peliculas/buscar")
//	public String buscar(Model model) {
//
//		Pelicula pedido = new Pelicula();
//		model.addAttribute("pedido", pedido);
//
//		return "crear-pedido-busquedaComercial";
//	}
//
//	@PostMapping("/peliculas/buscar")
//	public String buscar(Model model, @RequestParam("nombreBuscar")String nombreBuscar){
//
//			//obtengo la lista de los comerciales y filtro mediante streams
//			List<Comercial> listaBusqueda = this.comercialService.listAll();
//
//			listaBusqueda = listaBusqueda.stream()
//					.filter(c -> c.getNombre().toLowerCase().contains(nombreBuscar.toLowerCase()))
//					.collect(Collectors.toList());
//
//			if(listaBusqueda.isEmpty()) {
//
//				model.addAttribute("sinResultados", "No se encontraron resultados.");
//			}
//
//			model.addAttribute("listaBusqueda", listaBusqueda);
//
//			return "lista-busqueda-comercial";
//
//	}
//
//	@PostMapping("/peliculas/crear/{id_comercial}")
//	public String buscarCrear(Model model, @RequestParam("nombreBuscar")String nombreBuscar, @PathVariable Integer id_comercial ,@RequestParam("accion")String accion){
//
//		//obtengo la lista de los comerciales y filtro mediante streams
//		List<Idioma> listaBusqueda = this.clienteService.listAll();
//
//		listaBusqueda = listaBusqueda.stream()
//				.filter(c -> c.getNombre().toLowerCase().contains(nombreBuscar.toLowerCase()))
//				.collect(Collectors.toList());
//
//		if(listaBusqueda.isEmpty()) {
//
//			model.addAttribute("sinResultados", "No se encontraron resultados.");
//		}
//
//		model.addAttribute("accion", accion);
//		model.addAttribute("id_comercial", id_comercial);
//		model.addAttribute("listaBusqueda", listaBusqueda);
//
//		return "lista-busqueda-cliente";
//
//	}
//
//	@PostMapping("/peliculas/buscar/{id_comercial}")
//	public String buscar(Model model, @PathVariable Integer id_comercial){
//
//
//		model.addAttribute("id_comercial", id_comercial);
//
//		return "crear-pedido-busquedaIdioma";
//
//	}
//	//--------------------------------------------------------------------------------------------------^^^^^^
//
//	@GetMapping("/peliculas/{id}")
//	public String detalle(Model model, @PathVariable Integer id ) {
//
//		Pelicula pedido = peliculaService.one(id);
//		model.addAttribute("pedido", pedido);
//
//		return "detalle-pedido";
//
//	}
//
//	@GetMapping("/peliculas/editar/{id}")
//	public String editar(Model model, @PathVariable Integer id) {
//
//		Pelicula pedido = peliculaService.one(id);
//
//		PeliculaDTO peliculaDTO = pedidoMapper.pedidoAPeliculaFormDTO(pedido);
//		List<Idioma> listaIdiomas = this.clienteService.listAll();
//		List<Comercial> listaComerciales = this.comercialService.listAll();
//
//		model.addAttribute("listaComerciales", listaComerciales);
//		model.addAttribute("listaIdiomas", listaIdiomas);
//		model.addAttribute("peliculaDTO", peliculaDTO);
//
//		return "editar-pedido";
//
//	}
//
//	@PostMapping("/peliculas/editar/{id}")
//	public String submitEditar(Model model, @Valid @ModelAttribute("peliculaDTO") PeliculaDTO peliculaDTO, Errors errors) {
//
//		if(errors.hasErrors()){
//			model.addAttribute("peliculaDTO", peliculaDTO);
//
//			List<Idioma> listaIdiomas = this.clienteService.listAll();
//			model.addAttribute("listaIdiomas", listaIdiomas);
//
//			List<Comercial> listaComerciales = this.comercialService.listAll();
//			model.addAttribute("listaComerciales", listaComerciales);
//			return "editar-pedido";
//		}
//		Pelicula pedido = pedidoMapper.pedidoFormDTOAPelicula(peliculaDTO);
//		peliculaService.replacePelicula(pedido);
//
//		return "redirect:/peliculas";
//	}
//
//	@PostMapping("/peliculas/borrar/{id}")
//	public RedirectView submitBorrar(@PathVariable Integer id) {
//
//		peliculaService.deletePelicula(id);
//		return new RedirectView("/peliculas");
//
//	}



}
