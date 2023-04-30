package br.ufrb.edu.gcet236.sigrh.services;

//Pnde fica os métodos
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;

public class MedicamentosService {
    private ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
    
    
    //Getters e Setters
    public ArrayList<Medicamento> getMedicamentos() {
        return this.medicamentos;
    }

    public ResponseEntity<String> setMedicamentos(Medicamento placebo) {
        
        //Restiçoes de para adicionar o medicamento.
        if(placebo.getQuantidade() > 0 && placebo.getPesoEmGramas() > 0 && !placebo.getNome().isEmpty() && !placebo.getFabricante().isEmpty()){
            this.medicamentos.add(placebo); 
            return ResponseEntity.ok("ok");
        }
    
        //Caso quebre as restrições lanca-se uma exceção.
        throw new IllegalArgumentException("temos um parametro invalido");
    }
    
    public ArrayList<Medicamento> searchMedicamento(String identificador){
        ArrayList<Medicamento> medicamentosProcurados = new ArrayList<Medicamento>();
        for (Medicamento medicamento: this.medicamentos){// for varre o array de medicamentos, um a um e se contem a string ele adiciona no array de medicamentos procurados.

            //juntando o nome e o codigo do medicamento em uma string só
            String identificadorMedicamento = medicamento.getNome() + " " + medicamento.getCodigo();

            //verificando se o identificador bate com o codigo ou o nome do medicamento
            if(identificadorMedicamento.toLowerCase().contains(identificador.toLowerCase())){ //Contais recebe a string(Nome ou codigo) e procura no array.
                medicamentosProcurados.add(medicamento);
            }
            
        }

        return medicamentosProcurados;
    }
    //Remoção do medicamento
    public ResponseEntity<String> removeMedicamento(String codigo){
        //Iterando por cada medicamento no armario
        for (Medicamento medicamento: this.medicamentos){//Lê o array principal dos medicamentos

            if(medicamento.getCodigo().equals(codigo)){
                //Caso o codigo seja igual ao fornecido remova o medicamento.
                this.medicamentos.remove(medicamento);
                return new ResponseEntity<>("Medicamento removido com sucesso.", HttpStatus.OK);
            }
            
        }
        return new ResponseEntity<>("Medicamento não encontrado.", HttpStatus.NOT_FOUND);
    }
    //Lê os atributos do novo medicamento e susbstitui um a um do antigo medicamento.
    public ResponseEntity<String> editMedicamento(String codigoAntigo, String codigoNovo, int quantidade, int peso, boolean generico, boolean tarjaPreta, String nome, String fabricante, String info){
        //Crie um medicamento auxiliar 
        Medicamento medicamentoEditado = new Medicamento(codigoNovo, 
        quantidade, 
        peso,
        generico, 
        tarjaPreta, 
        nome, 
        fabricante, 
        info);

        for(int i = 0; i < this.medicamentos.size(); i++){
            //Compara com os já existentes com base no código.
            if(this.medicamentos.get(i).getCodigo().equals(codigoAntigo)){
                //Editando o medicamento. (Trocando o antigo pelo novo)
                this.medicamentos.set(i, medicamentoEditado);
                return new ResponseEntity<>("Medicamento editado com sucesso.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Medicamento não editado.", HttpStatus.NOT_FOUND);
    }
}
