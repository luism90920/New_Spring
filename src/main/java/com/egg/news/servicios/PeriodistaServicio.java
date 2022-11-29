
package com.egg.news.servicios;

import com.egg.news.entidades.Periodista;
import com.egg.news.entidades.Usuario;
import com.egg.news.entidades.noticia;
import com.egg.news.enumeraciones.Rol;
import com.egg.news.excepciones.MiExcepcion;
import com.egg.news.repositorios.PeriodistaRepositorio;
import com.egg.news.repositorios.UsuarioRepositorio;
import com.egg.news.repositorios.noticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodistaServicio implements UserDetailsService{
    
    @Autowired
    private PeriodistaRepositorio periodistaRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional
    public void registrar(String nombreUsuario, String password, String password2, Integer sueldoMensual) throws MiExcepcion{
        
        
        validar(nombreUsuario, password, password2);
        
        Periodista periodista = new Periodista();
        
        periodista.setAlta(new Date());
        periodista.setNombreUsuario(nombreUsuario);
        periodista.setPassword(new BCryptPasswordEncoder().encode(password));
        periodista.setActivo(Boolean.TRUE);
        periodista.setRol(Rol.ADMIN);
        periodista.setSueldoMensual(sueldoMensual);
        
        periodistaRepositorio.save(periodista);
    }

    private void validar(String nombreUsuario, String password, String password2) throws MiExcepcion{
        if (nombreUsuario.isEmpty() || nombreUsuario == null ) {
            throw new MiExcepcion("el nombre no puede se nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiExcepcion("La contraseña no puede estar vacio y debe tener más de 5 dígitos");
        }
        if (!password.equals(password2)) {
            throw new MiExcepcion("Las contraseñas ingresadas deben ser iguales");
        }
    }
    
    public List<Periodista> listarPeriodistas(){
        
        List<Periodista> periodistas = new ArrayList();
        
        periodistas = periodistaRepositorio.findAll(); //findAll trae todos las noticias de DATA BASE
        
        
        return periodistas;
    }
    

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        
        Periodista periodista = periodistaRepositorio.buscarPorNombreUsuario(nombreUsuario);
        
        if(periodista != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+periodista.getRol().toString());
            
            permisos.add(p);
            
            return new User(periodista.getNombreUsuario(), periodista.getPassword(), permisos);
        }else{
            return null;
        }
    }            
                
}
