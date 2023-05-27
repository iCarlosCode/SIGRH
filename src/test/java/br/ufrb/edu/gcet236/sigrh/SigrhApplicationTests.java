package br.ufrb.edu.gcet236.sigrh;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;
import br.ufrb.edu.gcet236.sigrh.entities.Medicamento;
import br.ufrb.edu.gcet236.sigrh.services.FornecedorService;
import br.ufrb.edu.gcet236.sigrh.services.MedicamentoService;

@WebMvcTest
@ContextConfiguration(classes=SigrhApplication.class)
class SigrhApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private FornecedorService fornecedorService;
	@SuppressWarnings("unused")
	private MedicamentoService medicamentoService;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void medicamentoPossuiFornecedorValido() throws Exception {
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome("Fornecedor 1");
		fornecedores.add(fornecedor);

		Mockito.when(fornecedorService.getAll()).thenReturn(fornecedores);

		MvcResult result = mockMvc.perform(get("/fornecedores/all")).andDo(MockMvcResultHandlers.print()).andReturn();
		System.out.println("OLha só " + result.getResponse().getContentAsString());

		Medicamento medicamento = new Medicamento(
			"D0001", 
			2, 
			300, 
			true,
			true,
			"Dipirona",
			"EMS",
			"Feito de farinha de mandioca.",
			"1234567891011");
		String json1 = mapper.writeValueAsString(medicamento);
		Medicamento medicamento2 = new Medicamento(
			"D0002", 
			20, 
			3500, 
			false,
			false,
			"Rivutriu",
			"CIMED",
			"Feito de farinha de arroz.",
			"000000");
		String json2 = mapper.writeValueAsString(medicamento2);

		Fornecedor fornecedor2 = new Fornecedor();
		fornecedor2.setCnpj("123456789101112");
		String json3 = mapper.writeValueAsString(fornecedor2);

		mockMvc.perform(post("/fornecedores/add").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
			.content(json3).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		mockMvc.perform(post("/api/armario/create/medicamento").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
			.content(json1).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		mockMvc.perform(post("/api/armario/create/medicamento").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(json2).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}

/*	@Test
	public void testPostExample() throws Exception {
		Student student = new Student();
		student.setId(1);
		student.setName("Mike");

		Mockito.when(studentService.saveStudent(ArgumentMatchers.any())).thenReturn(student);

		String json = mapper.writeValueAsString(student);
		mockMvc.perform(post(URIConstant.POST_MAPPING).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
			.content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
			.andExpect(jsonPath("$.id", Matchers.equalTo(1)))
			.andExpect(jsonPath("$.name", Matchers.equalTo("Mike")));
 }*/
}
