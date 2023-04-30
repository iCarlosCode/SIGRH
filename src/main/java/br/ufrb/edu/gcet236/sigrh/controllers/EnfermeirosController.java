package br.ufrb.edu.gcet236.sigrh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;

import br.ufrb.edu.gcet236.sigrh.services.EnfermeiroService;
import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;
import br.ufrb.edu.gcet236.sigrh.entities.Pessoa;

@RestController
@RequestMapping("api")
public class EnfermeirosController {
    EnfermeiroService hospital = new EnfermeiroService();
    ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello Enfermeiros!");
    }
    
    @GetMapping("/listar_enfermeiros")
    public ResponseEntity<ArrayList<Pessoa>> listarEnfermeiros() {
        return ResponseEntity.ok(hospital.getColaboradores());
    }

    @PostMapping(value = "/cadastrar")
    public String cadastrarEnfermeiro(@RequestBody Enfermeiro entrada) {
        hospital.cadastrarColaboradores(entrada);
        
        return hospital.getColaboradores().toString();
    }

    @PatchMapping(value = "/editar")
    public String editarEnfermeiro(@RequestParam String nome, String cpf, String rg, String telefone, String lotação, String cpfAntigo) {
        Enfermeiro enfermeiro = new Enfermeiro(nome, cpf, rg, telefone, lotação);
        hospital.editarColaboradores(enfermeiro, cpfAntigo);
        
        return hospital.getColaboradores().toString();
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<ArrayList<Enfermeiro>> buscarEnfermeiro(@RequestParam String nome, String cpf, String rg, String telefone, String lotacao) {
        ArrayList<Pessoa> resultadosDaBusca = null;
        enfermeiros.clear();
        if (!nome.isEmpty()) {
            resultadosDaBusca = this.hospital.buscarPorNome(nome);
        } 
        else if (cpf != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorCPF(cpf);
        }
        else if (rg != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorRG(rg);
        }
        else if (telefone != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorTelefone(telefone);
        }
        else if (lotacao != null) 
        {
            resultadosDaBusca = this.hospital.buscarPorLotação(lotacao);
        }
        
        if (resultadosDaBusca == null) 
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(enfermeiros);
                //"Nenhum enfermeiro encontrado, revise suas informações.");
        }

        for (var resultado : resultadosDaBusca) {
            if (resultado instanceof Enfermeiro)
            {
                enfermeiros.add((Enfermeiro) resultado);
            }
        }

        return ResponseEntity.ok(enfermeiros);
    }

    @DeleteMapping(value = "/remover")
    public ResponseEntity<String> removerEnfermeiro(@RequestParam String nome, String cpf, String rg, String telefone, String lotação) {
        var resultadoDaBusca = buscarEnfermeiro(nome, cpf, rg, telefone, lotação).getBody();

        if (resultadoDaBusca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum enfermeiro encontrado, revise suas informações.");
        }

        Enfermeiro enfermeiro = resultadoDaBusca.get(0);
        
        hospital.removerColaboradores(enfermeiro);
        return ResponseEntity.ok(enfermeiros.toString());
    }
}