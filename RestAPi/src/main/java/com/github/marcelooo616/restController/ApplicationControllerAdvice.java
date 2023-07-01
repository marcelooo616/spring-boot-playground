package com.github.marcelooo616.restController;


import com.github.marcelooo616.exception.PedidoNaoEncontradoExceptio;
import com.github.marcelooo616.exception.RegraNegocioException;
import com.github.marcelooo616.restController.dto.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {


    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handkeRegraNegocioException(RegraNegocioException e){
        String mensagemErro = e.getMessage();
        return new ApiErrors(mensagemErro);
    }


    @ExceptionHandler(PedidoNaoEncontradoExceptio.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoExceptio e){
        return new ApiErrors(e.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException e){
       List<String> errors =  e.getBindingResult().getAllErrors()
                .stream()
                .map( error -> error.getDefaultMessage())
                .collect(Collectors.toList());

       return new ApiErrors(errors);
    }


}
