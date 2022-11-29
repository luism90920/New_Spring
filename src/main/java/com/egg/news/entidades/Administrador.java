
package com.egg.news.entidades;

import com.egg.news.enumeraciones.Rol;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Administrador extends Periodista {

    public Administrador() {
    }

    
    public Administrador(List<noticia> misNoticias, Integer sueldoMensual, String nombreUsuario, String password, Rol rol, Boolean activo, Date alta) {
        super(misNoticias, sueldoMensual, nombreUsuario, password, rol, activo, alta);
    }
 
    
    
    
}
