package org.iesvdm.pruebaud3.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
public class Pelicula
{
    private int id_pelicula;
    private String titulo;
//    String text;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_lanzamiento;
    private Idioma idioma;
    private int duracion_alquiler;
    private BigDecimal rental_rate;
    private int duracion;
    private BigDecimal replacement_cost;
//    Date ultima_actualizacion;

    public Pelicula(){}
}
