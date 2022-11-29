
package com.egg.news.controladores;

import com.egg.news.excepciones.MiExcepcion;
import com.egg.news.servicios.UsuarioServicio;
import com.egg.news.servicios.noticiaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//RequestMapping: lo que hace es configurar cuál es la URL que va a escuchar a este controlador
//va a escuchar a partir de la "/"
//cada ves que pongamos en nuestra URL localhost:8080/ hace un llamado al controlador
@RequestMapping("/") 
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    //primer método que se va a ejecutar cuando iniciemos nuestra aplicación, cuando la abramos en localhost
    //GetMapping mapea la URL cuando se haya ingresado la "/"
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/registrar")
    public String registrar(){
        return "registroUsuario.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombreUsuario, @RequestParam String password, 
            @RequestParam String password2, ModelMap modelo){
            
            
        try {
            usuarioServicio.registrar(nombreUsuario, password, password2);
            
            modelo.put("exito", "Usuario registrado correctamente");
            
            return "index.html";
            
        } catch (MiExcepcion ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombreUsuario", nombreUsuario);
            return "registroUsuario.html";
        }
    }
    
    
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if (error != null) {
            modelo.put("error", "Usuario o contraseña invalidos");
        }
        return "login.html";
    }
    
   @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
   @GetMapping("/inicio")
   public String inicio(){
       return "inicio.html";
   }
}
