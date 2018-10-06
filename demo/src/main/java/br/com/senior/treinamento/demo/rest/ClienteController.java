package br.com.senior.treinamento.demo.rest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.services.ClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {
  
  //@Autowired
  private ClienteService clienteService;
  
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }
  
  @PostMapping("/clientes")
  public ResponseEntity<ClienteEntity> criar(@RequestBody ClienteEntity cliente) {
    System.out.println("Criando cliente");
    
    return new ResponseEntity<>(clienteService.salvar(cliente), HttpStatus.CREATED);
  }
  
  @PutMapping("/clientes")
  public ResponseEntity<Void> alterar(@RequestBody ClienteEntity cliente) {
    System.out.println("Alterando cliente");
    clienteService.salvar(cliente);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @DeleteMapping("/clientes/{id}")
  public ResponseEntity<String> remover(@PathVariable Long id) {
    System.out.println("Deletando cliente");
    clienteService.deletar(id);
    return new ResponseEntity<>("Cliente removido do BD",HttpStatus.NO_CONTENT);
  }
  
  @GetMapping("/clientes/{id}")
  public ResponseEntity<ClienteEntity> retornar(@PathVariable Long id) {
    System.out.println("Buscando cliente: " + id);
    ClienteEntity cliente = new ClienteEntity();
    cliente.setNome("Diogo");
    cliente.setId(id);
    
    return new ResponseEntity<ClienteEntity>(cliente, HttpStatus.OK);
  }
  
  @GetMapping("/clientes")
  public ResponseEntity<List<ClienteEntity>> buscarClientes(@RequestParam Long quantidade) {
    System.out.println("Buscando clientes");
    List<ClienteEntity> listaCliente = new ArrayList<>();
    for(Long ix=1L;ix<=quantidade;ix++) {
      ClienteEntity cliente  = new ClienteEntity();
      cliente.setId(ix);
      cliente.setNome("Diogo" + ix);
      listaCliente.add(cliente);
    }
    return new ResponseEntity<>(listaCliente, HttpStatus.OK);
  }
}
