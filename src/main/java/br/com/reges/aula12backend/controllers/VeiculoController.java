package br.com.reges.aula12backend.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.reges.aula12backend.modelos.Veiculo;
import br.com.reges.aula12backend.rdn.VeiculoRdn;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class VeiculoController {

  
    @GetMapping("/veiculos")
    public List<Veiculo> Get() {

        VeiculoRdn rdn = new VeiculoRdn();
        return rdn.obterTodos();

    }
  
    @GetMapping("/veiculos/{id}")
    public Veiculo GetById(@PathVariable("id") int id) {

        VeiculoRdn rdn = new VeiculoRdn();
        return rdn.obterPorId(id);
    }
    
    @PostMapping("/veiculos")
    public int Post(@RequestBody Veiculo pvei) throws SQLException {

        VeiculoRdn rdn = new VeiculoRdn();
        return rdn.inserir(pvei);

    }
    
    @PutMapping("veiculos/{id}")
    public int Put(@PathVariable(value = "id") int id, @RequestBody Veiculo pVeiculo) {
        VeiculoRdn rdn = new VeiculoRdn();
        if (rdn.obterPorId(id).getId() > 0) {
            return rdn.alterar(pVeiculo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "veiculo não encontrado");
        }
    }
   
    @DeleteMapping("veiculos/{id}")
    public int Delete(@PathVariable(value = "id") int id) {

        VeiculoRdn rdn = new VeiculoRdn();
        if (rdn.obterPorId(id).getId() > 0) {
            return rdn.excluir(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "veiculo não encontrado");
        }
    }

    

}