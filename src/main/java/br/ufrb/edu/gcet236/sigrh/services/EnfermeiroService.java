package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;
import br.ufrb.edu.gcet236.sigrh.entities.Pessoa;

public class EnfermeiroService {
    private ArrayList<Pessoa> colaboradores = new ArrayList<Pessoa>();
    
    //retorna a lista de colaboradores cadastrados no hospital.
    public ArrayList<Pessoa> getColaboradores() {
        return this.colaboradores;
    }
    
    //adiciona um objeto do tipo Pessoa à lista de colaboradores cadastrados no hospital.
    public void cadastrarColaboradores(Pessoa colaborador) {
        this.colaboradores.add(colaborador);
    }
    
    // Método para editar um colaborador existente no hospital
    public void editarColaboradores(Pessoa enfermeiro, String cpfAntigo){
        for (int i = 0; i < colaboradores.size(); i++ ) {
            Pessoa colaborador = colaboradores.get(i);
            String cpfDoColaborador = colaborador.getCpf();
            if (cpfAntigo.equalsIgnoreCase(cpfDoColaborador)) {
                colaboradores.set(i, enfermeiro);
            }
        }
    }

    // Método para remover um colaborador do hospital
    public void removerColaboradores(Pessoa colaborador) {
        this.colaboradores.remove(colaborador);
    }

    // Método para buscar colaboradores por nome
    public ArrayList<Pessoa> buscarPorNome(String nome) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            System.out.println(colaborador.getNome() + nome.contains(colaborador.getNome()) + nome);
            if (colaborador.getNome().contains(nome)) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }
    
    // busca na lista de colaboradores por CPF, ignorando o caso, e retorna uma lista de objetos do tipo Pessoa que possuem o CPF informado.
    public ArrayList<Pessoa> buscarPorCPF(String cpf) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (cpf.equalsIgnoreCase(colaborador.getCpf())) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }
    
    //busca na lista de colaboradores por RG, ignorando o caso, e retorna uma lista de objetos do tipo Pessoa que possuem o RG informado
    public ArrayList<Pessoa> buscarPorRG(String rg) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (rg.equalsIgnoreCase(colaborador.getRg())) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }

    public ArrayList<Pessoa> buscarPorTelefone(String telefone) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (telefone.equalsIgnoreCase(colaborador.getTelefone())) {
                resultados.add(colaborador);
            }
        }
        return resultados;
    }
    
    //busca na lista de colaboradores por lotação, ignorando o caso, e retorna uma lista de objetos do tipo Pessoa que possuem a lotação informada. 
    public ArrayList<Pessoa> buscarPorLotação(String lotação) {
        ArrayList<Pessoa> resultados = new ArrayList<Pessoa>();
        for (Pessoa colaborador : this.colaboradores) {
            if (colaborador instanceof Enfermeiro) { // Verifica se o colaborador é um enfermeiro
                Enfermeiro enfermeiro = (Enfermeiro) colaborador; // Realiza o cast do objeto para Enfermeiro
                String lotacaoDoColaborador = enfermeiro.getLotação(); // Obtém a lotação do enfermeiro
                System.out.println(lotacaoDoColaborador + " " + lotação);
                if (lotacaoDoColaborador.equalsIgnoreCase(lotação)){ // Compara a lotação do enfermeiro com a lotação informada
                    resultados.add(colaborador); // Adiciona o enfermeiro na lista de resultados
                }
            }
        }
        return resultados;
    }
}
