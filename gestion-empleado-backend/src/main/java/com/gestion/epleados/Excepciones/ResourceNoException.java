package com.gestion.epleados.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNoException extends RuntimeException{

    private static final long serialVersionUID =1L;

    public ResourceNoException (String message){
        super(message);
}

}
