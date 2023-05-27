package br.ufrb.edu.gcet236.sigrh.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEnfermeiro;
    private String cpfEnfermeiro;
    private String nomeMedicamento;
    private String codigoMedicamento;
    private int quantidadeMedicamento;
    
    // Gambiarra para o org.springframework.orm.jpa.JpaSystemException: No default constructor for entity
    public Historico() {
    
    }
    public Historico(String nomeEnfermeiro, String cpfEnfermeiro, String nomeMedicamento, String codigoMedicamento, int quantidadeMedicamento) {
        this.nomeEnfermeiro = nomeEnfermeiro;
        this.cpfEnfermeiro = cpfEnfermeiro;
        this.nomeMedicamento = nomeMedicamento;
        this.codigoMedicamento = codigoMedicamento;
        this.quantidadeMedicamento = quantidadeMedicamento;
    }
    public String getCpfEnfermeiro() {
        return cpfEnfermeiro;
    }
    public void setCpfEnfermeiro(String cpf_enfermeiro) {
        this.cpfEnfermeiro = cpf_enfermeiro;
    }
    
    public String getCodigoMedicamento() {
        return codigoMedicamento;
    }
    public void setCodigoMedicamento(String codigo_medicamento) {
        this.codigoMedicamento = codigo_medicamento;
    }
    
    public int getQuantidadeMedicamento() {
        return quantidadeMedicamento;
    }
    public void setQuantidadeMedicamento(int quantidade_medicamento) {
        this.quantidadeMedicamento = quantidade_medicamento;
    }
    
    public String getNomeEnfermeiro() {
        return nomeEnfermeiro;
    }
    public void setNomeEnfermeiro(String nomeEnfermeiro) {
        this.nomeEnfermeiro = nomeEnfermeiro;
    }
    
    public String getNomeMedicamento() {
        return nomeMedicamento;
    }
    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }
}
