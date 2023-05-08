package br.ufrb.edu.gcet236.sigrh.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;
import br.ufrb.edu.gcet236.sigrh.services.HistoricoService;

@RestController
@RequestMapping("api")
public class HistoricosController {
    @Autowired
    HistoricoService hospital;
    ArrayList<Historico> enfermeiros = new ArrayList<Historico>();


    @GetMapping("/oi")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Oi Sala de Medicamentos!");
    }
}
