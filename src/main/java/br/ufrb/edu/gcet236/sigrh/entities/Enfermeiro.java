package br.ufrb.edu.gcet236.sigrh.entities;

import jakarta.persistence.Entity;

@Entity
public class Enfermeiro extends Pessoa {
    private String lotacao;

    public Enfermeiro() {

    }

    // construtor com parâmetros
    public Enfermeiro(String nome, String cpf, String rg, String telefone, String lotacao) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setRg(rg);
        this.setTelefone(telefone);
        this.setLotacao(lotacao);
    }

    // getter e setter para a lotacao
    public String getLotacao() {
        return this.lotacao;
    }
    
    //define a lotacao do enfermeiro.
    public void setLotacao(String lotacao) {
        this.lotacao = lotacao;
    }

    // sobrescrita do método toString() para retornar um objeto JSON
    @Override
    public String toString() {

        // Retorna uma representação em formato JSON dos atributos lotação, rg, cpf, nome e telefone do enfermeiro.
        return "{" + " lotação='" + this.getLotacao() + "'" + ", rg='" + this.getRg() + "'" + ", cpf='" + this.getCpf() + "'"
                + ", nome='" + this.getNome() + "'" + ", telefone='" + this.getTelefone() + "'" + "}";
    }
}