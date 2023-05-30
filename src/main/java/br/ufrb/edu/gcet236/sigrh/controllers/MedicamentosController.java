package br.ufrb.edu.gcet236.sigrh.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufrb.edu.gcet236.sigrh.services.FornecedorService;
import br.ufrb.edu.gcet236.sigrh.services.MedicamentoService;
import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;
import br.ufrb.edu.gcet236.sigrh.responses.ItemBaixoEstoque;
import br.ufrb.edu.gcet236.sigrh.entities.EntradaCadastro;
import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;


/**
* ExampleController
*
* @author Daniel Padua
*/
@RestController
@RequestMapping("api/armario")
public class MedicamentosController { //Crie um novo armario
    @Autowired
    MedicamentoService armario; //Cria um array vazio no armario, onde se coloca, edita e retira medicamentos.

    @Autowired
    FornecedorService fornecedorService;
    
    //@Autowired
    Fornecedor fornecedores = new Fornecedor();
    int nextID = 0; //Auxilia na criação do codigo dos medicamentos. (Contador)

    //Pegue todos os medicamentos do armario e exibe na tela.
    @GetMapping("/get/medicamentos")
    public List<Medicamento> getMedicamentos() {
       // armario.setMedicamentos(medicamento);
        return armario.getMedicamentos();
    }

    @GetMapping("/get/medicamento")
    public ArrayList<Medicamento> getSearchMedicamento(@RequestParam String nome) { //RequestParam = Parametro que voce quer receber, nesse caso uma string.
       // armario.setMedicamentos(medicamento);
        //System.out.println(armario.searchMedicamento(nome)); 

        return armario.searchMedicamento(nome); //Pesquise pelo medicamento por letra(string) ou por código
    } 

    //Crie um novo medicamento
    @PostMapping("/create/medicamento") //Rota para criar um medicamento.
    public ResponseEntity<String> put(@RequestBody String medicamentoACadastrar) {      
        String json = medicamentoACadastrar;
        
        try{
            ObjectMapper mapper = new ObjectMapper();
            EntradaCadastro entradaCadastro = mapper.readValue(json, EntradaCadastro.class);
            RestTemplate restTemplate = new RestTemplate();
            var fornecedor = restTemplate.getForObject("http://localhost:8080/fornecedores/all", ArrayList.class); 
            System.out.println("FORNECEDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOR" + fornecedor.toString());
            
            if (fornecedor.isEmpty())// || !fornecedor.toString().contains(entradaCadastro.getCnpjFornecedor()))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fornecedor não cadastrado");
            }
            else if ((fornecedor != null) && fornecedor.toString().contains(entradaCadastro.getCnpjFornecedor()) == false)
            {
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fornecedor não cadastrado");
            }
            /*if (!mockMvc.perform(get("http://localhost:8080/fornecedores/all")).andDo(MockMvcResultHandlers.print()).getResponse().getContentAsString().contains("" + entradaCadastro.getCnpjFornecedor())) 
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fornecedor não cadastrado");
            }*/

            Medicamento medicamentoNovo = new Medicamento( //pegue os atributos da entrada cadastro e crie um novo medicamento. 
                entradaCadastro.generateId(entradaCadastro.getNome(), nextID),
                entradaCadastro.getQuantidade(), 
                entradaCadastro.getPesoEmGramas(),
                entradaCadastro.isStatusGenerico(),
                entradaCadastro.isStatusTarjaPreta(),
                entradaCadastro.getNome(),
                entradaCadastro.getFabricante(),
                entradaCadastro.getOutrasInformacoes(),
                entradaCadastro.getCnpjFornecedor());
                //Adicionando o primeiro medicamento
                if(armario.getMedicamentos().size() < 1){

                    armario.setMedicamentos(medicamentoNovo);
                    nextID++;
                }
                //adicionando os demais medicamentos
                else{

                    boolean jaExiste = false;
                    //Passeando por todos os medicamentos no armario
                    for (Medicamento remedio : armario.getMedicamentos()) {
                       // System.out.println("Medicamento: " + remedio.getNome());
    
                        //Verificando se já tem um remedio igual ao que vc está querendo cadastrar
                         if(remedio.equals(medicamentoNovo)){
                            //aumentando a quantidade do medicamento
                            jaExiste = true;
                            remedio.setQuantidade(remedio.getQuantidade() + medicamentoNovo.getQuantidade());
                            break;
                        }
                        
                    }
                    
                    if(!jaExiste){
                        //colocando o medicamento no armario
                        armario.setMedicamentos(medicamentoNovo);
                        nextID++;

                    }
                }
                    //Adicionando demais medicamentos no armario
            return ResponseEntity.status(HttpStatus.CREATED).body("Medicamento cadastrado com sucesso!");
        }catch(Exception e){
            e.printStackTrace();
            //System.out.println("Deu erro pae");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deu erro pae!");
        }
    }

    //Remoção de medicamentos
    /* @GetMapping("remove/medicamentos")
    public ResponseEntity<String> listaDeRemedios() {
        String lista = new String();
        for (Medicamento remedio : armario.getMedicamentos()) {
            lista += "Medicamento: " + remedio.getNome()
            + " Código: " + remedio.getCodigo() + "\n";
        }
        return ResponseEntity.ok(lista);
    } */


    @DeleteMapping("/remove/medicamento")
    public ResponseEntity<String> removeMedicamento(@RequestParam String codigo) {
       /* String lista = new String();
        for (Medicamento remedio : armario.getMedicamentos()) {
            lista += "Medicamento: " + remedio.getNome()
            + " Código: " + remedio.getCodigo() + "\n";
        }*/
        //System.out.println(lista);
        return armario.removeMedicamento(codigo);
    }

    @PatchMapping("/edit/medicamento")
    public ResponseEntity<String> editMedicamento(@RequestParam String codigo, 
    @RequestParam String quantidade,
    @RequestParam String peso,
    @RequestParam String generico,
    @RequestParam String tarjaPreta,
    @RequestParam String nome,
    @RequestParam String fabricante,
    @RequestParam String info,
    @RequestParam String cnpjFornecedor){

        return armario.editMedicamento(codigo, Integer.parseInt(quantidade), Integer.parseInt(peso), Boolean.parseBoolean(generico), Boolean.parseBoolean(tarjaPreta), nome, fabricante, info, cnpjFornecedor); 
    }   

    // GAMBIARRA
    @GetMapping("/buscar")
    public Medicamento buscarMedicamento(@RequestParam String codigo) {
        return armario.buscaPorCodigo(codigo);
    }
    //Teste 
    @GetMapping("/baixoEstoque")
    public ArrayList<ItemBaixoEstoque> getbaixoEstoqueMedicamentos() { 
        var itens = new ArrayList<ItemBaixoEstoque>();
        for (var m : armario.estoqueBaixoMedicamentos())
        {
            var fornecedor = fornecedorService.buscaPorPartedoCNPJ(m.getCnpjFornecedor()).get(0);
            String nomeFornecedor = fornecedor.getNome();
            String telefoneFornecedor = fornecedor.getTelefone();
            String nomeMedicamento = m.getNome();
            int quantidadeMedicamento = m.getQuantidade();
            
            itens.add(new ItemBaixoEstoque(nomeFornecedor, telefoneFornecedor, nomeMedicamento, quantidadeMedicamento));
        }
        return itens; 
    } 
}

    

