package com.aula04.banco.banco.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import com.aula04.banco.banco.utils.CPF;
import com.aula04.banco.banco.utils.SENHA;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class RequestCliente {

    @NotNull(message = "Não pode ser null") @NotEmpty(message = "Não pode ser vazio") @Length(min = 2)
    private String nome;
    private String email;
    @CPF
    private String cpf;
    @SENHA
    private String senha;
    private Integer agencia;

}
