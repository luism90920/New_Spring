
package com.egg.news.controladores;

import com.egg.news.entidades.Periodista;
import com.egg.news.excepciones.MiExcepcion;
import com.egg.news.servicios.PeriodistaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/periodista")
public class PeriodistaControlador {
    
    @Autowired
    public PeriodistaServicio periodistaServicio;
    
    @GetMapping("/registrar")
    public String registrar(){
        return "registroPeriodista.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String password, 
            @RequestParam String password2, @RequestParam Integer sueldoMensual, ModelMap modelo){
            
            
        try {
            periodistaServicio.registrar(nombreUsuario, password, password2, sueldoMensual);
            
            modelo.put("exito", "Periodista registrado correctamente");
            
            return "index.html";
            
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombreUsuario", nombreUsuario);
            return "registroPeriodista.html";
        }
    }
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        //Traemos el listado de noticias. Usamos el métoto "listarNoticias" de "notServ"
        //y lo guardamos en el listado "noticias"
        List<Periodista> periodistas = periodistaServicio.listarPeriodistas();
        
        //Inyectamos con nuestro "modelo" nuestros atributos bajo el nombre de llave "autores"
        //vamos a enviar la lista "noticias"
        modelo.addAttribute("periodistas", periodistas);
        
        //Retormanos un html
        
        return "periodista_list";
    }
}
