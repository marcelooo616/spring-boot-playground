package com.github.marcelooo616.restController.dto;

import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;



public class ApiErrors {

    @Getter
    private List<String> errors;


    public ApiErrors(String mensagemErro) {
        this.errors = Arrays.asList(mensagemErro);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
