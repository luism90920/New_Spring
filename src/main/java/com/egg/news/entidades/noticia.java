
package com.egg.news.entidades;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class noticia implements Comparable<noticia>{
    
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
   // @JoinColumn(name = "periodista_id")
    private Periodista creador;
    
    private String titulo;
    private String cuerpo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date alta;
    
    

    public noticia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Periodista getCreador() {
        return creador;
    }

    public void setCreador(Periodista creador) {
        this.creador = creador;
    }
    
    

    //METODO ABSTRACTO PARA ORDENAR POR FECHA DE ALTA
    @Override
    public int compareTo(noticia o) {
        return o.getAlta().compareTo(alta);
    }

    
    
    
}
