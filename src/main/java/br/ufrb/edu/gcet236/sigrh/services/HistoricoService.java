package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;

@Service
public class HistoricoService {
    private ArrayList<Historico> historicos = new ArrayList<Historico>();
    
    public ArrayList<Historico> getHistoricos() {
        return this.historicos;
    }
    
    public void cadastrarHistorico(Historico historico) {
        this.historicos.add(historico);
    }
    
    
    public void atualizarHistorico(Historico historicoNovo){
        for (int i = 0; i < historicos.size(); i++ ) {
            Historico historicoAntigo = historicos.get(i);
            String cpfEnfermeiro = historicoAntigo.getCpfEnfermeiro();
            String codigoMedicamento = historicoAntigo.getCodigoMedicamento();

            if (cpfEnfermeiro.equalsIgnoreCase(historicoNovo.getCpfEnfermeiro()) && codigoMedicamento.equalsIgnoreCase(historicoNovo.getCodigoMedicamento())) {
                historicos.set(i, historicoNovo);
            }
        }
    }

    /*// Método para remover um colaborador do hospital
    public void removerColaboradores(Pessoa colaborador) {
        this.colaboradores.remove(colaborador);
    }*/
    
    public ArrayList<Historico> buscarPorCPF(String cpf) {
        ArrayList<Historico> resultados = new ArrayList<Historico>();
        for (Historico h : this.historicos) {
            if (cpf.equalsIgnoreCase(h.getCpfEnfermeiro())) {
                resultados.add(h);
            }
        }
        return resultados;
    }
}
