package br.ufrb.edu.gcet236.sigrh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;

public interface FornecedorRespository extends JpaRepository<Fornecedor, Long> {
  
}
