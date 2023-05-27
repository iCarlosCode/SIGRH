package br.ufrb.edu.gcet236.sigrh.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;
import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;
import br.ufrb.edu.gcet236.sigrh.requests.MedicamentoParaRetirar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;
import br.ufrb.edu.gcet236.sigrh.responses.ItemHistorico;
import br.ufrb.edu.gcet236.sigrh.services.EnfermeiroService;
import br.ufrb.edu.gcet236.sigrh.services.HistoricoService;
import br.ufrb.edu.gcet236.sigrh.services.MedicamentoService;

@RestController
@RequestMapping("api")
public class HistoricosController {
    @Autowired
    HistoricoService historicosService;

    @Autowired
    EnfermeiroService enfermeiroService;

    @Autowired
    MedicamentoService medicamentoService;

    @GetMapping("/buscar_historico")
    public ResponseEntity<ArrayList<Historico>> buscarHistorico(@RequestParam String nomeEnfermeiro, String cpf, String nomeMedicamento, String codigo) {
        ArrayList<Historico> resultadosDaBusca = null;
        if (!nomeEnfermeiro.isEmpty()) {
            resultadosDaBusca = this.historicosService.buscarPorNomeEnfermeiro(nomeEnfermeiro);
        } 
        else if (cpf != null) 
        {
            resultadosDaBusca = this.historicosService.buscarPorCPFEnfermeiro(cpf);
        }
        else if (nomeMedicamento != null) 
        {
            resultadosDaBusca = this.historicosService.buscarPorNomeMedicamento(nomeMedicamento);
        }
        else if (codigo != null) 
        {
            resultadosDaBusca = this.historicosService.buscarPorCodigo(codigo);
        }

        return ResponseEntity.ok(resultadosDaBusca);
    }

    @PostMapping("/retirar_medicamento")
    public ResponseEntity<List<Historico>> retirar(@RequestBody MedicamentoParaRetirar medicamentoParaRetirar) {
        historicosService.addLog(medicamentoParaRetirar);
        return ResponseEntity.ok(historicosService.getHistoricos());
    }

    /*
    EXEMPLO DE UM POST QUE RECEBE JSON
    @PostMapping(value = "/cadastrar")
    public String cadastrarEnfermeiro(@RequestBody Enfermeiro entrada) {
        hospital.cadastrarColaboradores(entrada);

        return hospital.getColaboradores().toString();
    }*/
    @DeleteMapping("/deletar_historicos")
    public ResponseEntity<String> deletarTudo() {
        historicosService.deletarTudo();
        return ResponseEntity.ok("Historicos deletados com sucesso!");
    }
    
    @GetMapping("/listar_historicos")
    public ResponseEntity<ArrayList<ItemHistorico>> listarEnfermeiros() {
        List<Historico> historicos = historicosService.getHistoricos();
        ArrayList<ItemHistorico> itens = new ArrayList<ItemHistorico>();
        
        for (Historico h : historicos) 
        {
            var item = new ItemHistorico(
                h.getNomeEnfermeiro(), 
                h.getCpfEnfermeiro(), 
                h.getNomeMedicamento(), 
                h.getCodigoMedicamento(), 
                h.getQuantidadeMedicamento()
            );

            itens.add(item);
        }

        return ResponseEntity.ok(itens);
    }
}
