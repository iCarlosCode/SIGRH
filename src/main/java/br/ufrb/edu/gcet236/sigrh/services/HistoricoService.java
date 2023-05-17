package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;
import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;
import br.ufrb.edu.gcet236.sigrh.requests.MedicamentoParaRetirar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;

@Service
public class HistoricoService {
    private ArrayList<Historico> historicos = new ArrayList<Historico>();

    @Autowired
    private MedicamentoService medicamentoService;

    public ArrayList<Historico> getHistoricos() {
        return this.historicos;
    }

    public void cadastrarHistorico(Historico historico) {
        this.historicos.add(historico);
    }

    public void atualizarHistorico(Historico historicoNovo) {
        for (int i = 0; i < historicos.size(); i++) {
            Historico historicoAntigo = historicos.get(i);
            String cpfEnfermeiro = historicoAntigo.getCpfEnfermeiro();
            String codigoMedicamento = historicoAntigo.getCodigoMedicamento();

            if (cpfEnfermeiro.equalsIgnoreCase(historicoNovo.getCpfEnfermeiro()) && codigoMedicamento.equalsIgnoreCase(historicoNovo.getCodigoMedicamento())) {
                historicos.set(i, historicoNovo);
            }
        }
    }

    public void addLog(MedicamentoParaRetirar medicamentoParaRetirar) {
        for (Historico h : historicos) {
            if (h.getCpfEnfermeiro().equals(medicamentoParaRetirar.cpfEnfermeiro()) && h.getCodigoMedicamento().equals(medicamentoParaRetirar.codigoMedicamento())) {
                h.setQuantidadeMedicamento(h.getQuantidadeMedicamento() + medicamentoParaRetirar.quantidadeMedicamento());
                decrementarQtdMedicamento(medicamentoParaRetirar.codigoMedicamento(), medicamentoParaRetirar.quantidadeMedicamento());
            } else {
            }
            cadastrarHistorico(new Historico(medicamentoParaRetirar.cpfEnfermeiro(), medicamentoParaRetirar.codigoMedicamento(), medicamentoParaRetirar.quantidadeMedicamento()));
            decrementarQtdMedicamento(medicamentoParaRetirar.codigoMedicamento(), medicamentoParaRetirar.quantidadeMedicamento());
        }
    }

    public void decrementarQtdMedicamento(String codigoMedicamento, int qtd) {
        Medicamento m = medicamentoService.buscaPorCodigo(codigoMedicamento); // código para decrementar
        medicamentoService.editMedicamento(m.getCodigo(), m.getCodigo(), m.getQuantidade() - qtd, m.getPesoEmGramas(), m.isStatusGenerico(), m.isStatusTarjaPreta(), m.getNome(), m.getFabricante(), m.getOutrasInformacoes(), m.getCnpjFornecedor());
    }

    /*public ArrayList<Historico> getHistorc (String nomeEnfermeiro, String codEnfermeiro){
        ArrayList<Historico> logHistoric = new ArrayList<>();
        for (Historico historicos : historicos) {
        }

    }*/

    /*// Método para remover um colaborador do hospital
    public void removerColaboradores(Pessoa colaborador) {
        this.colaboradores.remove(colaborador);
    }*/

    public ArrayList<Historico> buscarPorCPFEnfermeiro(String cpfEnfermeiro) {
        ArrayList<Historico> resultados = new ArrayList<Historico>();
        for (Historico h : this.historicos) {
            if (cpfEnfermeiro.equalsIgnoreCase(h.getCpfEnfermeiro())) {
                resultados.add(h);
            }
        }
        return resultados;
    }
}
