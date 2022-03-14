package com.aula04.banco.banco.Controller;

import com.aula04.banco.banco.BancoAula04Application;
import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;
import com.aula04.banco.banco.dto.ResponseCliente;
import com.aula04.banco.banco.model.Cliente;
import com.aula04.banco.banco.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<ResponseCliente> clientes(Model model){
        return ResponseCliente.toResponse(clienteService.buscarTodosClientes());
    }

    @PostMapping
    public ResponseEntity<ResponseCliente> cadastrarCliente(@RequestBody @Valid RequestCliente requestCliente, UriComponentsBuilder uriComponentsBuilder){
        Cliente cliente = clienteService.cadastrarCliente(requestCliente);
        URI uri = uriComponentsBuilder.path("cliente/{id}").buildAndExpand(cliente.getId()).toUri();
       return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detalhesCleinte(@PathVariable UUID id) throws Exception{
        return ResponseEntity.ok(new ResponseCliente(clienteService.detalhesCliente(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCliente> atualizaCliente(@PathVariable UUID id, @RequestBody RequestCliente requestCliente) throws Exception {
        Cliente cliente = BancoAula04Application.bancoCliente.atualizarCliente(id, requestCliente);
        return ResponseEntity.ok(new ResponseCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarCliente(@PathVariable UUID id) throws Exception{
        BancoAula04Application.bancoCliente.deletarCliente(id);
        return ResponseEntity.ok().build();
    }
}
