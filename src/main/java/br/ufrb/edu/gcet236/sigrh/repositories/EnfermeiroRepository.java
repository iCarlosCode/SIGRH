package br.ufrb.edu.gcet236.sigrh.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;

//@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long> {

    @Query("SELECT enfermeiro FROM Enfermeiro enfermeiro WHERE enfermeiro.codigo LIKE %?1%")
    public List<Enfermeiro> search(String keyword);

}