
package com.egg.news.enumeraciones;


public enum Rol {
    //Solamente puede visualizar las noticias
    USER,
    
    //Visualiza, crea y modifica noticias
    ADMIN,
    
    //Visualiza, crea y modifica noticias. Además da de Alta(crea) o Baja a los periodistas
    SUPER_ADMIN;
    
}
