/* excepcion personalizada, para el caso de que no exista un usuario esta clase realizara una excepcion */

package com.springusuarios.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialversionID = 1L;

    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
