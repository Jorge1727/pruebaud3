package org.iesvdm.pruebaud3.mapper;

import org.iesvdm.pruebaud3.dto.PeliculaDTO;
import org.iesvdm.pruebaud3.modelo.Pelicula;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PeliculaMapper {
    @Mapping(source = "pelicula.idioma.id_idioma", target = "id_idioma")
    public PeliculaDTO peliculaAPeliculaFormDTO(Pelicula pelicula);

    @Mapping(source = "id_idioma", target = "idioma.id_idioma")
    public Pelicula peliculaFormDTOAPelicula(PeliculaDTO peliculaFormDTO);
}
