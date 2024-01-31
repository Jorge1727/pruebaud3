package org.iesvdm.pruebaud3.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.iesvdm.pruebaud3.modelo.Idioma;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class PeliculaDTO {
    private int id;

    @NotBlank(message = "{error.introducir.titulo}")
    @Size(min=4, message = "{error.nombre.size.min}")
    private String titulo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_lanzamiento;

    @Min(value=1, message = "{error.seleccione} {idioma}")
    private int id_idioma;
    private int duracion_alquiler;

    @Positive(message = "{error.mayoracero}")
    private BigDecimal rental_rate;

    @Positive(message = "{error.mayoracero}")
    private int duracion;

    @DecimalMin(value = "19.99", inclusive = true, message = "{error.replacement.size.min}")
    private BigDecimal replacement_cost;
    //    Date ultima_actualizacion;

    public PeliculaDTO(){}

}
