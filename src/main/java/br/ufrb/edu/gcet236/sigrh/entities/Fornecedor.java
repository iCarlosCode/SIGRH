package br.ufrb.edu.gcet236.sigrh.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fornecedor {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long ID;

  @Column(name = "cnpj", nullable = true, length = 14)
  private String cnpj;

  @Column(name = "nome", nullable = true, length = 100)
  private String nome;

  @Column(name = "endereco", nullable = false, length = 100)
  private String endereço;

  @Column(name = "telefone", nullable = true, length = 20)
  private String telefone;

  @Column(name = "email", nullable = false, length = 30)
  private String email;

  @Column(name = "foto", nullable = false, length = 100)
  private String foto;

  public String getCnpj() {
    return this.cnpj;
  }

  public void setCnpj(String cnpj) {
    this.cnpj = cnpj;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereço() {
    return this.endereço;
  }

  public void setEndereço(String endereço) {
    this.endereço = endereço;
  }
  
  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFoto() {
    return foto;
  }

  public void setFoto(String foto) {
    this.foto = foto;
  }
}

