
package com.egg.news.controladores;

import com.egg.news.entidades.Periodista;
import com.egg.news.entidades.noticia;
import com.egg.news.excepciones.MiExcepcion;
import com.egg.news.servicios.noticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia") //URL: localhost:8080/noticia
public class noticiaControlador {
    
    @Autowired
    private noticiaServicio notServ;
    
    @GetMapping("/registrar") //URL: localhost:8080/noticia/registrar
    public String registrar(ModelMap modelo){
        return "noticia_form.html";
    }
    
    //A través de PostMapping va a viajar la información, y esa información va a tener un action 
    //en una URL específica que era /noticia/registro (no se pone autor ya que estamos en noticia)
    //Se pone directamente "/registro"
    @PostMapping("/registro")
    //Creamos un método que va a recibir el título y el cuerpo
    //Los parámetros que llegan a este método se llaman de la mismma manera que el atributo name 
    //del "input" de "noticia_form.html"
    //Para indicarle al controlador que éste es un parámetro que va a viajar en la URL lo marcamos 
    //con un @RequestParam. Hace que sea un parámetro requerido
    //Mediante "modelo" inyecto los mensajes de error para que los vea el usuario
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, @RequestParam String creador, ModelMap modelo){
        
        try {
            
            notServ.crearNoticia(titulo, cuerpo, creador);
            modelo.put("exito", "La noticia fué creada correctamente");
           
            
        } catch (MiExcepcion ex) {
            
            //en "modelo.put" traemos el 
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        
         return "inicio.html";
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        //Traemos el listado de noticias. Usamos el métoto "listarNoticias" de "notServ"
        //y lo guardamos en el listado "noticias"
        List<noticia> noticias = notServ.listarNoticias();
        
        //Inyectamos con nuestro "modelo" nuestros atributos bajo el nombre de llave "autores"
        //vamos a enviar la lista "noticias"
        modelo.addAttribute("noticias", noticias);
        
        //Retormanos un html
        
        return "noticia_list";
    }
    
    @GetMapping("/mostrar/{id}")
    public String mostrar(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia", notServ.getOne(id));
        
        return "noticia_vista.html";
    }
    
    //Para que este método sepa qué noticia tiene que modificar tiene que viajar el "id" y para esto se utiliza
    //PathVariable. Esta anotación indica que este valor determinado va a viajar a través de un Path, es decir
    //a través de un fragmento de la URL en el que se encuentra determinado recurso
    //A través de la URL enviamos determinado recurso, es este caso el "id"
    
    
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo){
        modelo.put("noticia", notServ.getOne(id));
        
        return "noticia_modificar.html";
    }
    
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String titulo, String cuerpo, ModelMap modelo){
        try {
            notServ.modificar(id, titulo, cuerpo);
            
            return "redirect:../lista";
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            
            return "noticia_modificar.html";
        }
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo){
        try {
            notServ.eliminar(id);
            //modelo.put("exito", "La noticia fué eliminada correctamente");
            return "redirect:../lista";
        } catch (MiExcepcion ex) {
            //modelo.put("error", ex.getMessage());
                    
            return "redirect:../lista";
        }
        
        
    }
    
}
