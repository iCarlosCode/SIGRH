package br.ufrb.edu.gcet236.sigrh.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrb.edu.gcet236.sigrh.entities.Historico;


public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    
    @Query("SELECT historico FROM Historico historico WHERE historico.cpfEnfermeiro LIKE %?1% AND historico.codigoMedicamento LIKE %?2%")
    public Historico findByCpfEnfermeiroAndCodigoMedicamento(String cpfEnfermeiro, String codigoMedicamento);

    ArrayList<Historico> findByCpfEnfermeiro(String cpfEnfermeiro);
    List<Historico> findByCpfEnfermeiroContaining(String cpfEnfermeiro);
}
