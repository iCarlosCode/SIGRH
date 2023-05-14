package br.ufrb.edu.gcet236.sigrh.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;
import br.ufrb.edu.gcet236.sigrh.responses.ItemHistorico;
import br.ufrb.edu.gcet236.sigrh.services.EnfermeiroService;
import br.ufrb.edu.gcet236.sigrh.services.HistoricoService;
import br.ufrb.edu.gcet236.sigrh.services.MedicamentoService;

@RestController
@RequestMapping("api")
public class HistoricosController {
    @Autowired
    HistoricoService hospital;

    @Autowired
    EnfermeiroService enfermeiroService;

    @Autowired
    MedicamentoService medicamentoService;

    ArrayList<Historico> historicos = new ArrayList<Historico>();

    @GetMapping("/oi")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Oi Sala de Medicamentos!");
    }

    @GetMapping("/listar_historicos")
    public ResponseEntity<ArrayList<ItemHistorico>> listarEnfermeiros() {
        if (historicos.size() == 0) {
            historicos.add(new Historico("12345678910", "A0000", 4));
            historicos.add(new Historico("12345678911", "50001", 8));
            historicos.add(new Historico("12345678912", "L0004", 2));
        }

        var itens = new ArrayList<ItemHistorico>();
        for (Historico historico : historicos) {
            var enfermeiro = enfermeiroService.buscarPorCPF(historico.getCpfEnfermeiro());
            var medicamento = medicamentoService.buscaPorCodigo(historico.getCodigoMedicamento());
            if (!enfermeiro.isEmpty()) {
                var nomeEnfermeiro = enfermeiro.get(0).getNome();
                var nomeMedicamento = medicamento.getNome();
    
                itens.add(new ItemHistorico(nomeEnfermeiro, historico.getCpfEnfermeiro(), nomeMedicamento, historico.getCodigoMedicamento(), historico.getQuantidadeMedicamento()));
            }
            // Criar uma data class com tudo que é necessario para o historico
            // Mandar isso como response
        }
        return ResponseEntity.ok(itens);
    }
}
