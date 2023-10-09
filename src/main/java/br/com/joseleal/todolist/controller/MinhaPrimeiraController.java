package br.com.joseleal.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MinhaPrimeiraController {

    /**
     * Métodos de acesso do HTTP
     * GET - Buscar uma informacão
     * POST - Adicionar um dados/informação 
     * PUT - Alterar um dado/info
     * DELETE - Remover um dado
     * PATCH - Alterar somente uma parte da info/dado
     */
    
    // Método de uma classe
    @GetMapping("/mensagem")
    public String primeiraMensagem(){
        return "Funcionou";
    }
}
