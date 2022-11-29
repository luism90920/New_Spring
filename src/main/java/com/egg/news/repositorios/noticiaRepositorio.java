
package com.egg.news.repositorios;

import com.egg.news.entidades.noticia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface noticiaRepositorio extends JpaRepository<noticia, String>{
 
    @Query("SELECT n FROM noticia n WHERE n.titulo = :titulo")
    public noticia buscarPorTitulo(@Param("titulo") String titulo);
    
//    @Query("SELECT n FROM noticia WHERE n.cuerpo like '%cuerpo%'")
//    public Noticia buscarPorCuerpo (@Param("cuerpo") String cuerpo);

    @Override
    public noticia getOne(String id);

    public Optional<noticia> findById(Integer id);
    
}
