package br.ufrb.edu.gcet236.sigrh.entities;

public class Enfermeiro extends Pessoa {
    private String lotação;

    // construtor com parâmetros
    public Enfermeiro(String nome, String cpf, String rg, String telefone, String lotação) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setRg(rg);
        this.setTelefone(telefone);
        this.setLotação(lotação);
    }

    // getter e setter para a lotação
    public String getLotação() {
        return this.lotação;
    }
    
    //define a lotação do enfermeiro.
    public void setLotação(String lotação) {
        this.lotação = lotação;
    }

    // sobrescrita do método toString() para retornar um objeto JSON
    @Override
    public String toString() {

        // Retorna uma representação em formato JSON dos atributos lotação, rg, cpf, nome e telefone do enfermeiro.
        return "{" + " lotação='" + this.getLotação() + "'" + ", rg='" + this.getRg() + "'" + ", cpf='" + this.getCpf() + "'"
                + ", nome='" + this.getNome() + "'" + ", telefone='" + this.getTelefone() + "'" + "}";
    }
}