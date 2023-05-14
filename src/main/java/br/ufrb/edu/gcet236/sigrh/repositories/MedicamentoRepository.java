package br.ufrb.edu.gcet236.sigrh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;

//@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query("SELECT medicamento FROM Medicamento medicamento WHERE medicamento.codigo LIKE %?1%")
    public List<Medicamento> search(String keyword);

}