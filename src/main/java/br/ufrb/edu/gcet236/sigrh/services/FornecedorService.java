package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;
import br.ufrb.edu.gcet236.sigrh.repositories.FornecedorRespository;

@Service
public class FornecedorService {

  private ArrayList<Fornecedor> listaDeFornecedores = new ArrayList<>();
  private ArrayList<String> listaDeNomesECNPJs = new ArrayList<>();
  private static String FORNECEDOR_NAO_ENCONTRADO = "O fornecedor procurado não se encontra no banco de dados";

  @Autowired
  private FornecedorRespository fornecedorRespository;

  public ArrayList<Fornecedor> getAll() {
    return (ArrayList<Fornecedor>) fornecedorRespository.findAll();
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
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRespository.findAll();
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
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRespository.findAll();
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
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRespository.findAll();
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
    listaDeFornecedores = (ArrayList<Fornecedor>) fornecedorRespository.findAll();
    int size = parteDoNomeOuCNPJ.length();
    
    for(Fornecedor fornecedor : listaDeFornecedores) {
      String nome = fornecedor.getNome().replace(" ", "");
      String cnpj = fornecedor.getCnpj().replace(" ", "");
      if(nome.length() < size)  {
        String partCNPJ = cnpj.substring(0, size);
        if(compareStrings(partCNPJ, parteDoNomeOuCNPJ)) {
          fornecedores.add(fornecedor);
        }
      }
      else if(cnpj.length() < size) {
        String partNome = nome.substring(0, size);
        if(compareStrings(partNome, parteDoNomeOuCNPJ)) {
          fornecedores.add(fornecedor);
        }
      } 
      else {
        String partCNPJ = cnpj.substring(0, size);
        String partNome = nome.substring(0, size);
        if(compareStrings(partNome, parteDoNomeOuCNPJ) || compareStrings(partCNPJ, parteDoNomeOuCNPJ)) {
          fornecedores.add(fornecedor);
        }
      }
    }
    return fornecedores;
  }

  // ********** REMOVER ************ //
 
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

  // ********** ATUALIZAR ************ //

  //atualização por cnpj
  public void updatePorCnpj(String cnpj, Fornecedor novoFornecedor){
    ListIterator<Fornecedor> it = listaDeFornecedores.listIterator();

    while(it.hasNext()) {
      Fornecedor fornecedor = it.next();
      if(compareStrings(fornecedor.getCnpj(), cnpj)) {
        it.set(novoFornecedor);
      }
    }
  }
//atualização por nome
  public void updatePorNome(String nome, Fornecedor novoFornecedor){
    ListIterator<Fornecedor> it = listaDeFornecedores.listIterator();

    while(it.hasNext()) {
      Fornecedor fornecedor = it.next();
      if(compareStrings(fornecedor.getNome(), nome)){
        it.set(novoFornecedor);
      }
    }
  }

  // ********** REUTILIZAVEIS ************ //

  public boolean compareStrings(String string1, String string2) {
    return string1.replace(" ", "").equalsIgnoreCase(string2.replace(" ",""));
  }
}