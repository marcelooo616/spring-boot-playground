package com.github.marcelooo616.restController;


import com.github.marcelooo616.exception.RegraNegocioException;
import com.github.marcelooo616.restController.dto.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handkeRegraNegocioException(RegraNegocioException e){
        String mensagemErro = e.getMessage();
        return new ApiErrors(mensagemErro);
    }


}
