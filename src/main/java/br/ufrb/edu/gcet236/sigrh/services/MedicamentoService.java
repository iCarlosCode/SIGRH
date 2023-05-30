package br.ufrb.edu.gcet236.sigrh.services;

//Pnde fica os métodos
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;
import br.ufrb.edu.gcet236.sigrh.repositories.MedicamentoRepository;

import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;
@Service
public class MedicamentoService {
    @Autowired
    private MedicamentoRepository medicamentoRepository;
    
    //Getters e Setters
    public List<Medicamento> getMedicamentos() {
        //return this.medicamentos;
        return medicamentoRepository.findAll();
    }

    public ResponseEntity<String> setMedicamentos(Medicamento placebo) {
        
        //Restiçoes de para adicionar o medicamento.
        if(placebo.getQuantidade() > 0 && placebo.getPesoEmGramas() > 0 && !placebo.getNome().isEmpty() && !placebo.getFabricante().isEmpty()){
            //this.medicamentos.add(placebo); 
            medicamentoRepository.save(placebo);
            return ResponseEntity.ok("ok");
        }
    
        //Caso quebre as restrições lanca-se uma exceção.
        throw new IllegalArgumentException("temos um parametro invalido");
    }
    
    public ArrayList<Medicamento> searchMedicamento(String identificador){
        //ArrayList<Medicamento> medicamentosProcurados = new ArrayList<Medicamento>();
        // DO fazer as buscas usando o medicamentoRepository usar o historicoRepository como exemplo
        /*for (Medicamento medicamento: this.medicamentos){// for varre o array de medicamentos, um a um e se contem a string ele adiciona no array de medicamentos procurados.

            //juntando o nome e o codigo do medicamento em uma string só
            String identificadorMedicamento = medicamento.getNome() + " " + medicamento.getCodigo();

            //verificando se o identificador bate com o codigo ou o nome do medicamento
            if(identificadorMedicamento.toLowerCase().contains(identificador.toLowerCase())){ //Contais recebe a string(Nome ou codigo) e procura no array.
                medicamentosProcurados.add(medicamento);
            }
            
        }*/

            ArrayList<Medicamento> listatemp = medicamentoRepository.findByCodigoOrNome(identificador, identificador);
            ArrayList<Medicamento> lista = medicamentoRepository.findByCodigo(identificador);
            for (Medicamento dupMed : listatemp) {
                if (!lista.contains(dupMed)) {
                    lista.add(dupMed);
                }
            }
            return lista;
    }
    //Remoção do medicamento
    public ResponseEntity<String> removeMedicamento(String codigo){
        //Iterando por cada medicamento no armario
        /*for (Medicamento medicamento: this.medicamentos){//Lê o array principal dos medicamentos

            if(medicamento.getCodigo().equals(codigo)){
                //Caso o codigo seja igual ao fornecido remova o medicamento.
                //this.medicamentos.remove(medicamento);
                medicamentoRepository.delete(medicamento);
                return new ResponseEntity<>("Medicamento removido com sucesso.", HttpStatus.OK);
            }
            
        }*/
        Medicamento medicamento = medicamentoRepository.findByCodigo(codigo).get(0);
        if(medicamento != null){
            medicamentoRepository.delete(medicamento);
            return new ResponseEntity<>("Medicamento removido com sucesso.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Medicamento não encontrado.", HttpStatus.NOT_FOUND);
    }
    //Lê os atributos do novo medicamento e susbstitui um a um do antigo medicamento.
    public ResponseEntity<String> editMedicamento(String codigo, int quantidade, int peso, boolean generico, boolean tarjaPreta, String nome, String fabricante, String info, String cnpjFornecedor){
        //Crie um medicamento auxiliar 
        Medicamento medicamentoEditado = medicamentoRepository.findByCodigo(codigo).get(0);
        
        if (medicamentoEditado == null){
            return new ResponseEntity<>("Medicamento não editado.", HttpStatus.NOT_FOUND);
        }

        medicamentoEditado.setQuantidade(quantidade);
        medicamentoEditado.setPesoEmGramas(peso);
        medicamentoEditado.setStatusGenerico(generico);
        medicamentoEditado.setStatusTarjaPreta(tarjaPreta);
        medicamentoEditado.setNome(nome);
        medicamentoEditado.setFabricante(fabricante);
        medicamentoEditado.setOutrasInformacoes(info);
        medicamentoEditado.setCnpjFornecedor(cnpjFornecedor);

        medicamentoRepository.save(medicamentoEditado);
        return new ResponseEntity<String>("Medicamento editado com sucesso.", HttpStatus.OK);
    }

    // GAMBIARRA
    public Medicamento buscaPorCodigo(String codigo){
        return medicamentoRepository.findByCodigo(codigo).get(0);
    }

    public ArrayList<Medicamento> buscaPorCodigoOuNome(String codigo, String nome){
        return medicamentoRepository.findByCodigoOrNome(codigo, nome);
    }

    public void somarComQuantidadeMedicamento(String codigo, int quantidadeParaSomar) {
        Medicamento m = buscaPorCodigo(codigo);
        if (quantidadeParaSomar + m.getQuantidade() >= 0) {
            m.setQuantidade(quantidadeParaSomar + m.getQuantidade());
        }
        medicamentoRepository.save(m);
    }
    public ArrayList<Medicamento> estoqueBaixoMedicamentos(){
        /*ArrayList<String> medicamentosEstoqueBaixo = new ArrayList<String>();

        for (Medicamento medicamento: this.medicamentos){// for varre o array de medicamentos, um a um e se contem a string ele adiciona no array de medicamentos procurados.
            for (Fornecedor fornecedor: this.fornecedores){
                if(medicamento.getQuantidade()<10){
                    String fornecedorBaixoEstoque= fornecedor.getNome() + fornecedor.getTelefone();
                    if (medicamento.getCnpjFornecedor()==fornecedor.getCnpj()){
                        medicamentosEstoqueBaixo.add(fornecedorBaixoEstoque);
                }
            }
            }
        }
        return medicamentosEstoqueBaixo;*/
        return medicamentoRepository.medicamentosBaixoEstoque();
    } 
}
