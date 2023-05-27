package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;
import java.util.List;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;
import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;
import br.ufrb.edu.gcet236.sigrh.repositories.HistoricoRepository;
import br.ufrb.edu.gcet236.sigrh.requests.MedicamentoParaRetirar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;

@Service
public class HistoricoService {
    private ArrayList<Historico> historicos = new ArrayList<Historico>();

    @Autowired
    private MedicamentoService medicamentoService;

    @Autowired
    private HistoricoRepository historicoRepository;

    public List<Historico> getHistoricos() {
        return this.historicoRepository.findAll();
    }

    public void cadastrarHistorico(Historico historico) {
        this.historicoRepository.save(historico);
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

    public void addLog(MedicamentoParaRetirar m) {
        /*for (Historico h : historicos) {
            if (h.getCpfEnfermeiro().equals(medicamentoParaRetirar.cpfEnfermeiro()) && h.getCodigoMedicamento().equals(medicamentoParaRetirar.codigoMedicamento())) {
                h.setQuantidadeMedicamento(h.getQuantidadeMedicamento() + medicamentoParaRetirar.quantidadeMedicamento());
                decrementarQtdMedicamento(medicamentoParaRetirar.codigoMedicamento(), medicamentoParaRetirar.quantidadeMedicamento());
            } else {
            }
            cadastrarHistorico(new Historico(medicamentoParaRetirar.cpfEnfermeiro(), medicamentoParaRetirar.codigoMedicamento(), medicamentoParaRetirar.quantidadeMedicamento()));
            decrementarQtdMedicamento(medicamentoParaRetirar.codigoMedicamento(), medicamentoParaRetirar.quantidadeMedicamento());
        }*/
        
        Historico historico = historicoRepository.findByCpfEnfermeiroAndCodigoMedicamento(m.cpfEnfermeiro(), m.codigoMedicamento());

        if (historico == null)
        {
            historico = new Historico(m.nomeEnfermeiro(), m.cpfEnfermeiro(), m.nomeMedicamento(), m.codigoMedicamento(), m.quantidadeMedicamento());
        }
        else
        {
            historico.setQuantidadeMedicamento(m.quantidadeMedicamento() + historico.getQuantidadeMedicamento());
        }
        
        cadastrarHistorico(historico);
        this.medicamentoService.somarComQuantidadeMedicamento(m.codigoMedicamento(), -m.quantidadeMedicamento());
    }

    public int calcularQuantidadeMedicamento(MedicamentoParaRetirar m) {
        Historico historicoAntigo = historicoRepository.findByCpfEnfermeiroAndCodigoMedicamento(m.cpfEnfermeiro(), m.codigoMedicamento());

        if (historicoAntigo == null)
        {
            return m.quantidadeMedicamento();
        }
        else
        {
            return m.quantidadeMedicamento() + historicoAntigo.getQuantidadeMedicamento();
        }
        
    }
    public void decrementarQtdMedicamento(String codigoMedicamento, int qtd) {
        Medicamento m = this.medicamentoService.buscaPorCodigo(codigoMedicamento); // código para decrementar
        this.medicamentoService.somarComQuantidadeMedicamento(codigoMedicamento, qtd);
        /*this.medicamentoService.editMedicamento(m.getCodigo(), m.getCodigo(), m.getQuantidade() - qtd, m.getPesoEmGramas(), m.isStatusGenerico(), m.isStatusTarjaPreta(), m.getNome(), m.getFabricante(), m.getOutrasInformacoes(), m.getCnpjFornecedor());*/
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

    public ArrayList<Historico> buscarPorNomeEnfermeiro(String nomeEnfermeiro) {
        return historicoRepository.findByNomeEnfermeiroContaining(nomeEnfermeiro);
    }

    public ArrayList<Historico> buscarPorCPFEnfermeiro(String cpfEnfermeiro) {
        return historicoRepository.findByCpfEnfermeiro(cpfEnfermeiro);
    }
    
    public ArrayList<Historico> buscarPorNomeMedicamento(String nomeMedicamento) {
        return historicoRepository.findByNomeMedicamentoContaining(nomeMedicamento);
    }
    
    public ArrayList<Historico> buscarPorCodigo(String codigo) {
        return historicoRepository.findByCodigoMedicamento(codigo);
    }

    public void deletarTudo() {
        this.historicoRepository.deleteAll();
    }
}
