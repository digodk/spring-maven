package br.com.senior.treinamento.demo.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

  // @Autowired
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
    if (!clienteService.checkById(cliente.getId())) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    clienteService.salvar(cliente);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/clientes/{id}")
  public ResponseEntity<String> remover(@PathVariable Long id) {
    System.out.println("Solicitado deletar cliente id: " + id);
    if (!clienteService.checkById(id)) {
      System.out.println("Cliente não existe, retornando 404");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    System.out.println("Deletando cliente");
    clienteService.deletar(id);
    return new ResponseEntity<>("Cliente removido do BD", HttpStatus.NO_CONTENT);
  }

  @GetMapping("/clientes/{id}")
  public ResponseEntity<ClienteEntity> retornar(@PathVariable Long id) {
    System.out.println("Buscando cliente: " + id);
    Optional<ClienteEntity> resultado = clienteService.buscarPorId(id);
    if (!resultado.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
  }

  @GetMapping("/clientes")
  public ResponseEntity<List<ClienteEntity>> buscarClientes(
      @RequestParam(value = "quantidade", required = false) Long quantidade,
      @RequestParam(value = "email", required = false) String email) {
    System.out.println("Buscando clientes, quantidade: " + quantidade);
    List<ClienteEntity> listaCliente;
    if (!(email == null)) {
      listaCliente = clienteService.buscarPorEmail(email);
    } else {
      listaCliente = clienteService.buscarClientes();
    }
    if (!(quantidade == null)) {
      listaCliente = listaCliente.stream().limit(quantidade).collect(Collectors.toList());
    }
    return new ResponseEntity<List<ClienteEntity>>(listaCliente, HttpStatus.OK);
  }
}
