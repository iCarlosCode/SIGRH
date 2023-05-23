package br.ufrb.edu.gcet236.sigrh.controllers;

import java.util.ArrayList;
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

    ArrayList<Historico> historicos = new ArrayList<Historico>();

    @GetMapping("/buscar_historico")
    public ResponseEntity<ArrayList<Historico>> buscarHistorico(@RequestParam String nome, String cpf) {
        return ResponseEntity.ok(historicosService.buscarPorCPFEnfermeiro(cpf));
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

    @GetMapping("/listar_historicos")
    public ResponseEntity<ArrayList<ItemHistorico>> listarEnfermeiros() {
        List<Historico> historicos = historicosService.getHistoricos();
        ArrayList<ItemHistorico> itens = new ArrayList<ItemHistorico>();
        
        for (Historico historico : historicos) 
        {

            // E se deletarem o enfermeiro ou o medicamento? AI não tem nome né? E o historico como funciona?
            var nomeEnfermeiro = enfermeiroService.buscarPorCPF(historico.getCpfEnfermeiro()).get(0).getNome();
            var nomeMedicamento = medicamentoService.buscaPorCodigo(historico.getCodigoMedicamento()).getNome();

            var item = new ItemHistorico("nomeEnfermeiro", historico.getCpfEnfermeiro(), nomeMedicamento, historico.getCodigoMedicamento(), historico.getQuantidadeMedicamento());

            itens.add(item);
        }
        /*if (historicos.size() == 0) {
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
        }*/

        return ResponseEntity.ok(itens);
    }
}
