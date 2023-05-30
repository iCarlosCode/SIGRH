package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;
import br.ufrb.edu.gcet236.sigrh.repositories.FornecedorRespository;

@Service
public class FornecedorService {

  private ArrayList<Fornecedor> listaDeFornecedores = new ArrayList<>();
  private ArrayList<String> listaDeNomesECNPJs = new ArrayList<>();

  @Autowired
  private FornecedorRespository fornecedorRepository;

  public ArrayList<Fornecedor> getAll() {
    return (ArrayList<Fornecedor>) fornecedorRepository.findAll();
  }
  
  // ********** BUSCA ************ //

  //retorna lista de nomes e cnpjs
  public ArrayList<String> listNameAndCnpj() {
    listaDeNomesECNPJs.clear();
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRepository.findAll();
    for(Fornecedor fornecedor : listaDeFornecedores) {
      String nomeECNPJ = "Nome: "+fornecedor.getNome()+"; CNPJ: "+fornecedor.getCnpj();
      listaDeNomesECNPJs.add(nomeECNPJ);
    }

    return listaDeNomesECNPJs;
  }

  // Função de busca por nome 
  public Fornecedor buscaPorNome(String nome) {
    return fornecedorRepository.findByNome(nome);
  }

  // função de busca por cnpj
  public Fornecedor buscaPorCnpj(String cnpj) {
    return fornecedorRepository.findByCnpj(cnpj);
  } 

  // Função de busca por parte do nome ou cnpj
  public ArrayList<Fornecedor> buscaPorPartedoNomeOuCNPJ(String parteDoNomeOuCNPJ) {
    ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    fornecedores.addAll(fornecedorRepository.findByCnpjContaining(parteDoNomeOuCNPJ));
    fornecedores.addAll(fornecedorRepository.findByNomeContaining(parteDoNomeOuCNPJ));

    return fornecedores;
  }

  // ********** REMOVER ************ //
  
  /* remove por cnpj */
  public Fornecedor removePorCnpj(String cnpj) {
    Fornecedor fornecedorDeletado = fornecedorRepository.findByCnpj(cnpj);

    fornecedorRepository.delete(fornecedorDeletado);
    return fornecedorDeletado;
  }

  /* remove por nome */
  public Fornecedor removePorNome(String nome) {
    Fornecedor fornecedorDeletado = fornecedorRepository.findByNome(nome);

    fornecedorRepository.delete(fornecedorDeletado);

    return fornecedorDeletado;
    }

  // ********** ATUALIZAR ************ //

  //atualização por cnpj
  public Fornecedor updatePorCnpj(String cnpj, Fornecedor novoFornecedor){
    Fornecedor fornecedorModificado = fornecedorRepository.findByCnpj(cnpj);
   
    fornecedorRepository.delete(fornecedorModificado);
    fornecedorRepository.save(novoFornecedor);

    return fornecedorModificado;
  }

  //atualização por nome
  public Fornecedor updatePorNome(String nome, Fornecedor novoFornecedor){
   Fornecedor fornecedorModificado = fornecedorRepository.findByNome(nome);

   fornecedorRepository.delete(fornecedorModificado);
   fornecedorRepository.save(novoFornecedor);

   return fornecedorModificado;
  }

  // ********** REUTILIZAVEIS ************ //

  public boolean compareStrings(String string1, String string2) {
    return string1.replace(" ", "").equalsIgnoreCase(string2.replace(" ",""));
  }
}