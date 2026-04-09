package com.bosonit.springmongo.repositorio;

import com.bosonit.springmongo.utils.Utils;
import org.springframework.stereotype.Repository;

@Repository
public class RepoSeguridad {

    private final Utils utils;

    public RepoSeguridad(Utils utils) {
        this.utils = utils;
    }

    /**
     * FUNCIÓN QUE VALIDA LA ENTRADA DE UN USUARIO
     * @param username
     * @param password
     * @return true or false
     */
    public boolean validar(String username,String password){
        return (username.equals("admin") && password.equals(utils.getPassword()));
    }
}
