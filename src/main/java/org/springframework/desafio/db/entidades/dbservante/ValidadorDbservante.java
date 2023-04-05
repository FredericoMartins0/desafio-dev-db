package org.springframework.desafio.db.entidades.dbservante;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValidadorDbservante implements Validator {
    private static final String REQUIRED = "required";


    @Override
    public boolean supports(Class<?> clazz) {
        return Dbservante.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object objeto, Errors erros) {
        Dbservante dbservante = (Dbservante) objeto;
        String nome = dbservante.obterNome();
        if(!StringUtils.hasLength(nome)){
            erros.rejectValue("nome",REQUIRED,REQUIRED);
        }
    }
}
