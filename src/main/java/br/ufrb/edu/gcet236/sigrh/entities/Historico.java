package br.ufrb.edu.gcet236.sigrh.entities;

public class Historico {
    private String cpfEnfermeiro;
    private String codigoMedicamento;
    private int quantidadeMedicamento;

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
