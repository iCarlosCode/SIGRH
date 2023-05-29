package br.ufrb.edu.gcet236.sigrh.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;

public interface FornecedorRespository extends JpaRepository<Fornecedor, Long> {
    ArrayList<Fornecedor> findByCnpj(String cnpj);
    ArrayList<Fornecedor> findByCnpjContaining(String cnpj);

    ArrayList<Fornecedor> findByNome(String nome);
    ArrayList<Fornecedor> findByNomeContaining(String nome);
}
