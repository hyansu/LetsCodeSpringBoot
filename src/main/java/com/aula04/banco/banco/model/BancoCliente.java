package com.aula04.banco.banco.model;

import com.aula04.banco.banco.dto.RequestCliente;
import com.aula04.banco.banco.dto.RequestDeposito;

import java.util.*;

public class BancoCliente {

    private static List<Cliente> clientes = new ArrayList<>();

    public void adicionar(Cliente cliente){
        BancoCliente.clientes.add(cliente);
    }

    public List<Cliente> buscaClientes(){
        return BancoCliente.clientes;
    }

    public Cliente detalheCliente(UUID id) throws Exception{
       Optional<Cliente>  resultCliente =  BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id)).findAny();
       if(resultCliente.isPresent()){
           return resultCliente.get();
       }else {
           throw new Exception("Usuário não encontrado");
       }
    }

    public Cliente atualizarCliente(UUID id, RequestCliente requestCliente) throws Exception{
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id)).forEach(cliente -> {
            cliente.setSenha(requestCliente.getNome());
            cliente.setEmail(requestCliente.getEmail());
            cliente.setSenha(requestCliente.getSenha());
        });

        return detalheCliente(id);
    }

    public void deletarCliente(UUID id) throws Exception{
        Cliente cliente = detalheCliente(id);
        BancoCliente.clientes.remove(cliente);
    }

    public void deposita(UUID id, RequestDeposito requestDeposito){
        BancoCliente.clientes.stream().filter(cliente -> Objects.equals(cliente.getId(), id))
                .forEach(cliente -> {
                    Optional<Conta> resultConta = cliente.getContas().stream().filter(conta -> Objects.equals(conta.getId(), requestDeposito.getConta())).findAny();
                    if(resultConta.isPresent()){
                        Double novoSaldo = resultConta.get().getSaldo() + requestDeposito.getValor();
                        resultConta.get().setSaldo(novoSaldo);
                    }else{
                        try {
                            throw new Exception("Conta não encontrada");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
    }


}
