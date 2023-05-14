package br.ufrb.edu.gcet236.sigrh.entities;
//Onde fica os atributos
// import com.fasterxml.jackson.databind.ObjectMapper;
 // version 2.11.1
//import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */

//Auxilia Conversão de JSON para POJO.
public class EntradaCadastro{
    //Declaração das atributos publicas
    public String codigo;
    public int quantidade;
    public int pesoEmGramas;
    
    
    public boolean statusGenerico;
    public boolean statusTarjaPreta;
    
    public String nome;
    public String fabricante;
    public String outrasInformacoes;
    public String cnpjFornecedor;
 

    
    public String generateId(String nome, int nextID) {
        String id = String.format("%04d", nextID);
        return nome.charAt(0) + id; //primeiraletra do nome mais um numero (P004), começando do 001
    }
    
    //GETTERS E SETTERS
    
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public int getPesoEmGramas() {
        return pesoEmGramas;
    }
    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }
    
    public boolean isStatusGenerico() {
        return statusGenerico;
    }
    public void setStatusGenerico(boolean statusGenerico) {
        this.statusGenerico = statusGenerico;
    }
    public boolean isStatusTarjaPreta() {
        return statusTarjaPreta;
    }
    public void setStatusTarjaPreta(boolean statusTarjaPreta) {
        this.statusTarjaPreta = statusTarjaPreta;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getFabricante() {
        return fabricante;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }
    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }
    
    public String getCnpjFornecedor() {
        return cnpjFornecedor;
    }
    public void setCnpjFornecedor(String cnpjFornecedor) {
        this.cnpjFornecedor = cnpjFornecedor;
    }
}
