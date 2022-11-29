
package com.egg.news.servicios;

import com.egg.news.entidades.Usuario;
import com.egg.news.enumeraciones.Rol;
import com.egg.news.excepciones.MiExcepcion;
import com.egg.news.repositorios.PeriodistaRepositorio;
import com.egg.news.repositorios.UsuarioRepositorio;
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
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    
    @Transactional
    public void registrar(String nombreUsuario, String password, String password2) throws MiExcepcion{
        
        validar(nombreUsuario, password, password2);
        
        Usuario usuario = new Usuario();
        
        usuario.setAlta(new Date());
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setActivo(Boolean.TRUE);
        usuario.setRol(Rol.USER);
        
        usuarioRepositorio.save(usuario);
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

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        
        Usuario usuario = usuarioRepositorio.buscarPorNombreUsuario(nombreUsuario);
        
        if(usuario != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            
            permisos.add(p);
            
            return new User(usuario.getNombreUsuario(), usuario.getPassword(), permisos);
        }else{
            return null;
        }
                
                
    }
            
            
}
