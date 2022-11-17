
package com.egg.news.controladores;

import com.egg.news.servicios.noticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//RequestMapping: lo que hace es configurar cuál es la URL que va a escuchar a este controlador
//va a escuchar a partir de la "/"
//cada ves que pongamos en nuestra URL localhost:8080/ hace un llamado al controlador
@RequestMapping("/") 
public class PortalControlador {
    
    @Autowired
    private noticiaServicio notServ;
    
    //primer método que se va a ejecutar cuando iniciemos nuestra aplicación, cuando la abramos en localhost
    //GetMapping mapea la URL cuando se haya ingresado la "/"
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
}
