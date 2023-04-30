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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.ufrb.edu.gcet236.sigrh.entities.Fornecedor;
import br.ufrb.edu.gcet236.sigrh.services.FornecedorService;

@WebMvcTest
@ContextConfiguration(classes=SigrhApplication.class)
//@SpringBootTest
class SigrhApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private FornecedorService fornecedorService;


	@Test
	void contextLoads() {
	}

	@Test
	void medicamentoPossuiFornecedorValido() throws Exception {
		ArrayList<Fornecedor> fornecedores = new ArrayList<>();
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj("123456789");

		Mockito.when(fornecedorService.getListaDeFornecedores()).thenReturn(fornecedores);
		mockMvc.perform(get("/fornecedores/all")).andDo(MockMvcResultHandlers.print());
		//resultActions.andDo(MockMvcResultHandlers.print());
	}

	/*@Test
	public void testGetExample() throws Exception {
		List<Student> students = new ArrayList<>();
		Student student = new Student();
		student.setId(1);
		student.setName("Mike");
		students.add(student);
		Mockito.when(studentService.getStudents()).thenReturn(students);
		mockMvc.perform(get(URIConstant.GET_MAPPING)).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1))).andExpect(jsonPath("$[0].name", Matchers.equalTo("Mike")));
 }*/
}
