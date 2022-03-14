package com.aula04.banco.banco.Controller;

import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.service.OperacoesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/operacoes")
public class OperacoesController {

    OperacoesService operacoesService;

    @PatchMapping("/deposita")
    public ResponseEntity deposita(@RequestHeader UUID id, @RequestBody RequestDeposito requestDeposito) throws Exception{
        operacoesService.depositar(id, requestDeposito);
        return ResponseEntity.ok().build();
    }

}
