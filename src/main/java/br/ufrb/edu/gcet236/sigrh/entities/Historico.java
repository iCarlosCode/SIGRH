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
    private String cpfEnfermeiro;
    private String codigoMedicamento;
    private int quantidadeMedicamento;
    
    // Gambiarra para o org.springframework.orm.jpa.JpaSystemException: No default constructor for entity
    public Historico() {
    
    }
    public Historico(String cpfEnfermeiro, String codigoMedicamento, int quantidadeMedicamento) {
        this.cpfEnfermeiro = cpfEnfermeiro;
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
}
