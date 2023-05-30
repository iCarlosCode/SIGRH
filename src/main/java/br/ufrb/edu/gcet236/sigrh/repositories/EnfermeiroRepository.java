package br.ufrb.edu.gcet236.sigrh.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;

//@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long> {

    //@Query("SELECT enfermeiro FROM Enfermeiro enfermeiro WHERE enfermeiro.codigo LIKE %?1%")
    //public List<Enfermeiro> search(String keyword);

    ArrayList<Enfermeiro> findByCpf(String cpf);
    ArrayList<Enfermeiro> findByRg(String rg);
    ArrayList<Enfermeiro> findByTelefoneContaining(String telefone);
    ArrayList<Enfermeiro> findByNomeContaining(String nome);
    ArrayList<Enfermeiro> findByLotacaoContaining(String lotacao);
}