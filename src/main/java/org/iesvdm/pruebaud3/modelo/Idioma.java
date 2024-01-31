package org.iesvdm.pruebaud3.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class Idioma
{
    int id_idioma;
    String nombre;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date ultima_actualizacion;

    public Idioma(){}
}
