package com.bosonit.SpringMongo.repositorio;

import org.springframework.stereotype.Repository;

@Repository
public class RepoSeguridad {
    
    /**
     * FUNCIÓN QUE VALIDA LA ENTRADA DE UN USUARIO
     * @param username
     * @param password
     * @return true or false
     */
    public boolean validar(String username,String password){
        return (username.equals("admin") && password.equals("admin"));
    }
}
