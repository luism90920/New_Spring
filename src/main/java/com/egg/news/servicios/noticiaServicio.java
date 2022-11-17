
package com.egg.news.servicios;

import com.egg.news.entidades.noticia;
import com.egg.news.excepciones.MiExcepcion;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.egg.news.repositorios.noticiaRepositorio;
import java.util.Date;

@Service
public class noticiaServicio {
    
    @Autowired
    noticiaRepositorio noticiaRepositorio;
    
    //CREAR
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiExcepcion{
        
        validar(titulo, cuerpo);
        
        noticia noticia = new noticia();
        
        noticia.setAlta(new Date());
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        
        noticiaRepositorio.save(noticia); //save recive una entidad por parámetro y la persiste en la DATA BASE
    }
    
    
    //CONSULTAR LISTADO
    public List<noticia> listarNoticias(){
        
        List<noticia> noticias = new ArrayList();
        
        noticias = noticiaRepositorio.findAll(); //findAll trae todos las noticias de DATA BASE
        
        
        return noticias;
    }
    
    @Transactional
    public void modificar(Integer id, String titulo, String cuerpo) throws MiExcepcion{
        
        validar(titulo, cuerpo);
        
        //Optional hace que si el valor está presente devuelve TRUE y nos puede retornar el valor que se 
        //guarda en respuesta
        Optional<noticia> respuesta = noticiaRepositorio.findById(id);
        
        //Si la respuesta está presente recien ahí vamos a modificar nuestra noticia
        if(respuesta.isPresent()){ 
            noticia noticia = respuesta.get(); //traemmos la noticia con GET
            
            //SETEAMOS lo que queremos modificar
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);
            
            noticiaRepositorio.save(noticia);
        }
    }
    
    //Trae la noticia a través del "id"
    public noticia getOne(String id){
        return noticiaRepositorio.getOne(id);
    }
    
  
    
    //VALIDAR
    private void validar(String titulo, String cuerpo) throws MiExcepcion{
        
        if(titulo.isEmpty() || titulo == null){
            throw new MiExcepcion("el título no puede ser nulo o estar vacío");
        }
        
        if(cuerpo.isEmpty() || cuerpo == null){
            throw new MiExcepcion("el cuerpo no puede ser nulo o estar vacío");
        }
    }
    
}
