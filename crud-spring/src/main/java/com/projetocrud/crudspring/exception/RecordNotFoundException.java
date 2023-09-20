package com.projetocrud.crudspring.exception;

public class RecordNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public RecordNotFoundException(String id) {
        super("Registro não encontrado com o id ou name: " + id);
    }
}
