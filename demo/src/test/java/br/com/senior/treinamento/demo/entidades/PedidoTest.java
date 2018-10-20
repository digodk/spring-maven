package br.com.senior.treinamento.demo.entidades;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.senior.treinamento.demo.DemoApplication;
import br.com.senior.treinamento.demo.respository.PedidoRepository;
import br.com.senior.treinamento.demo.services.ClienteService;
import br.com.senior.treinamento.demo.services.PedidoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class PedidoTest {

  
  @Autowired
  private PedidoService pedidoService;
  
  @Autowired
  private ClienteService clienteService;
  
  @Autowired
  private PedidoRepository pedidoRepository;
  
  private static ClienteEntity cliente;
  
  @Before
  public void setUp() {
    
    cliente = new ClienteEntity();
    cliente.setNome("Diogo");
    cliente.setEmail("bbbabb");
    cliente = clienteService.salvar(cliente);
  }
  
  @Test
  public void deveCriarPedido() {
    PedidoEntity pedido = new PedidoEntity();
    pedido.setData(LocalDateTime.now());
    pedido.setCliente(cliente);
    pedido = pedidoService.salvar(pedido);
    
    assertNotNull(pedido.getId());
  }
  
  @Test
  public void deveContarPedidos() {
    Long quantidadeAnterior = pedidoRepository.count();
    ClienteEntity mockCliente = new ClienteEntity();
    mockCliente.setNome("shhh bb");
    mockCliente.setEmail("it's ok");
    cliente = clienteService.salvar(mockCliente);
    PedidoEntity mockPedido = new PedidoEntity();
    mockPedido.setCliente(mockCliente);
    mockPedido.setData(LocalDateTime.now());
    pedidoService.salvar(mockPedido);
    Long quantidadeApos = pedidoRepository.count();
    assertNotEquals(quantidadeAnterior, quantidadeApos);
  }
}
