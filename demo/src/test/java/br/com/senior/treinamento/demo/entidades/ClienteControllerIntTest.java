package br.com.senior.treinamento.demo.entidades;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import br.com.senior.treinamento.demo.DemoApplication;
import br.com.senior.treinamento.demo.rest.ClienteController;
import br.com.senior.treinamento.demo.services.ClienteService;
import br.com.senior.treinamento.demo.util.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class ClienteControllerIntTest {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  private MockMvc restClienteMockMvc;
  private ClienteEntity cliente;

  @Before
  public void setup() {
    final ClienteController clienteController = new ClienteController(clienteService);
    restClienteMockMvc = MockMvcBuilders.standaloneSetup(clienteController)
        .setMessageConverters(jacksonMessageConverter).build();
  }

  @Before
  public void initTest() {
    cliente = initCriarCliente();
  }

  private static ClienteEntity initCriarCliente() {
    ClienteEntity mockCliente = new ClienteEntity();
    mockCliente.setNome("Diogo");
    mockCliente.setEmail("ccccc");
    return mockCliente;
  }

  @Test
  public void criarCliente() throws IOException, Exception {
    restClienteMockMvc
        .perform(post("/api/clientes").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cliente)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void buscarCliente() throws Exception {
    cliente = clienteService.salvar(cliente);

    restClienteMockMvc.perform(MockMvcRequestBuilders.get("/api/clientes/{id}", cliente.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cliente.getId().intValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(cliente.getNome()));
  }
}
