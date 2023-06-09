package br.ufrb.edu.gcet236.sigrh.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;

//@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    @Query("SELECT DISTINCT medicamento FROM Medicamento medicamento WHERE medicamento.codigo LIKE %?1% or medicamento.nome LIKE %?2% ")
    public ArrayList<Medicamento> findByCodigoOrNome(String codigo, String nome);  

    @Query("SELECT medicamento FROM Medicamento medicamento WHERE medicamento.quantidade < 10")
    public ArrayList<Medicamento> medicamentosBaixoEstoque();

    public ArrayList<Medicamento> findByCodigo(String codigo);
    
    public ArrayList<Medicamento> findByCnpjFornecedor(String cnpjFornecedor);


    public ArrayList<Medicamento> findByNome(String nome);
}