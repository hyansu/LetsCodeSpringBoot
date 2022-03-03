package com.aula04.banco.banco.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErroValidacao {

    private String campo;
    private String messagem;

}
