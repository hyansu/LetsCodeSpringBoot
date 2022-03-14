package com.aula04.banco.banco.dto;

import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.model.Conta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class RequestCliente {

    @NotNull(message = "Não pode ser null") @NotEmpty(message = "Não pode ser vazio") @Length(min = 2)
    private String nome;
    private String email;
    private String senha;
    private Integer agencia;

}
