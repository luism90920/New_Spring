
package com.egg.news.entidades;

import com.egg.news.enumeraciones.Rol;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Periodista extends Usuario{
    
    @OneToMany
    private List<noticia> misNoticias;
    
    private Integer sueldoMensual;

    public Periodista() {
    }

    
    public Periodista(List<noticia> misNoticias, Integer sueldoMensual, String nombreUsuario, String password, Rol rol, Boolean activo, Date alta) {
        super(nombreUsuario, password, rol, activo, alta);
        this.misNoticias = misNoticias;
        this.sueldoMensual = sueldoMensual;
    }

    public List<noticia> getMisNoticias() {
        return misNoticias;
    }

    public void setMisNoticias(List<noticia> misNoticias) {
        this.misNoticias = misNoticias;
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    
}
