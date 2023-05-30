package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrb.edu.gcet236.sigrh.controllers.FornecedoresController;
import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;
import br.ufrb.edu.gcet236.sigrh.repositories.FornecedorRespository;

@Service
public class FornecedorService {

  private ArrayList<Fornecedor> listaDeFornecedores = new ArrayList<>();
  private ArrayList<String> listaDeNomesECNPJs = new ArrayList<>();
  private static String FORNECEDOR_NAO_ENCONTRADO = "O fornecedor procurado não se encontra no banco de dados";

  @Autowired
  private FornecedorRespository fornecedorRepository;

  public ArrayList<Fornecedor> getAll() {
    return (ArrayList<Fornecedor>) fornecedorRepository.findAll();
  }
  


  /* 
  public ArrayList<Fornecedor> getListaDeFornecedores() {
    return this.listaDeFornecedores;
  }
 

// Adiciona o fornecedor caso ele já não se encontre na lista
  public void addFornecedor(Fornecedor fornecedor) {
    if(!pertenceALista(fornecedor)) listaDeFornecedores.add(fornecedor);
  }
 

//Compara se o cnpj do fornecedor encontra-se na lista _________ATUALIZADO__________
  public boolean pertenceALista(Fornecedor fornecedor) {

  }
  */

  // ********** BUSCA ************ //

  //retorn lista de nomes e cnpjs
  public ArrayList<String> listNameAndCnpj() {
    listaDeNomesECNPJs.clear();
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRepository.findAll();
    for(Fornecedor fornecedor : listaDeFornecedores) {
      String nomeECNPJ = "Nome: "+fornecedor.getNome()+"; CNPJ: "+fornecedor.getCnpj();
      listaDeNomesECNPJs.add(nomeECNPJ);
    }

    return listaDeNomesECNPJs;
  }

  // Função de busca por CNPJ
  /* 
  public Object buscaPorCNPJ(String cnpj) {
    return dao.findById(cnpj);
  }
  */


  // Função de busca por nome *********************
  public Object buscaPorNome(String nome) {
    for(Fornecedor fornecedor : listaDeFornecedores) {
      if(compareStrings(fornecedor.getNome(), nome)) {
        return fornecedor;
      }
    }

    return FORNECEDOR_NAO_ENCONTRADO;
  }

  // Função de busca por parte do nome
  public ArrayList<Fornecedor> buscaPorParteDoNome(String parteDoNome) {
    ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRepository.findAll();
    String parteNome = parteDoNome.replace(" ", "");
    int size = parteNome.length();
    
    for(Fornecedor fornecedor : listaDeFornecedores) {
      String nome = fornecedor.getNome().replace(" ", "");
      if(nome.length() >= size) {
        String part = nome.substring(0, size);
        if(part.equalsIgnoreCase(parteNome)) {
          if(!fornecedores.contains(fornecedor)) {
            fornecedores.add(fornecedor);
          }
        }
      }
    }

    return fornecedores;
  }

  // Função de busca por parte do cnpj
  public ArrayList<Fornecedor> buscaPorPartedoCNPJ(String parteDoCNPJ) {
    ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRepository.findAll();
    String parteCNPJ = parteDoCNPJ.replace(" ", "");
    int size = parteCNPJ.length();
    
    for(Fornecedor fornecedor : listaDeFornecedores) {
      String nome = fornecedor.getCnpj().replace(" ", "");
      if(nome.length() >= size) {
        String part = nome.substring(0, size);
        if(part.equalsIgnoreCase(parteCNPJ)) {
          if(!fornecedores.contains(fornecedor)) {
            fornecedores.add(fornecedor);
          }
        }
      }
    }

    return fornecedores;
  }

  // Função de busca por parte do nome ou cnpj
  public ArrayList<Fornecedor> buscaPorPartedoNomeOuCNPJ(String parteDoNomeOuCNPJ) {
    ArrayList<Fornecedor> fornecedores = new ArrayList<>();
    fornecedores.addAll(fornecedorRepository.findByCnpjContaining(parteDoNomeOuCNPJ));
    fornecedores.addAll(fornecedorRepository.findByNomeContaining(parteDoNomeOuCNPJ));

    return fornecedores;
  }

  // ********** REMOVER ************ //
  
  public Fornecedor removePorCnpj(String cnpj) {
    Fornecedor fornecedorDeletado = fornecedorRepository.findByCnpj(cnpj);

    fornecedorRepository.delete(fornecedorDeletado);
    return fornecedorDeletado;
  }

  public Fornecedor removePorNome(String nome) {
    Fornecedor fornecedorDeletado = fornecedorRepository.findByNome(nome);

    fornecedorRepository.delete(fornecedorDeletado);

    return fornecedorDeletado;
    }

  /* 
  public Fornecedor removeporCnpj(String cnpj) {
    ListIterator<Fornecedor> it = listaDeFornecedores.listIterator();
    Fornecedor fornecedor_removido = new Fornecedor();

    while(it.hasNext()) {
      Fornecedor fornecedor = it.next();
      fornecedor_removido = fornecedor;
      if(compareStrings(fornecedor.getCnpj(), cnpj)) {
        it.remove();
        break;
      }
    }

    return fornecedor_removido;
  }

  // Função de remoção por nome
  public Fornecedor removePorNome(String nome) {
    ListIterator<Fornecedor> it = listaDeFornecedores.listIterator();
    Fornecedor fornecedor_removido = new Fornecedor();

    while(it.hasNext()) {
      Fornecedor fornecedor = it.next();
      fornecedor_removido = fornecedor;
      if(compareStrings(fornecedor.getNome(), nome)) {
        it.remove();
        break;
      }
    }

    return fornecedor_removido;
  }
  */

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