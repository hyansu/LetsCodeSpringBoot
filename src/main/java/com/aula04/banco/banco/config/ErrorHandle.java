package com.aula04.banco.banco.config;

import com.aula04.banco.banco.exeptions.ErroValidacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandle {
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidacao> handleErroValidacao(MethodArgumentNotValidException exception){
        List<ErroValidacao> erros = new ArrayList<>();
        List<FieldError> camposErro = exception.getBindingResult().getFieldErrors();

        camposErro.forEach( erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ErroValidacao erroValidacao = new ErroValidacao(erro.getField(), mensagem);
            erros.add(erroValidacao);
        });

        return erros;
    }
}
