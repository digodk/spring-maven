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
import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;
import br.com.senior.treinamento.demo.services.PedidoService;

@RestController
@RequestMapping("/api")
public class PedidoController {

  private PedidoService pedidoService;

  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @PostMapping("/pedidos")
  public ResponseEntity<PedidoEntity> criar(@RequestBody PedidoEntity pedido) {
    System.out.println("Criando um novo pedido");
    System.out.println("Cliente do pedido: " + pedido.getCliente().getId());

    return new ResponseEntity<>(pedidoService.salvar(pedido), HttpStatus.CREATED);
  }

  @PutMapping("/pedidos")
  public ResponseEntity<Void> alterar(@RequestBody PedidoEntity pedido) {
    System.out.println("Alterando cliente");
    if (!pedidoService.checkById(pedido.getId())) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } ;
    pedidoService.salvar(pedido);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/pedidos")
  public ResponseEntity<List<PedidoEntity>> buscarClientes(
      @RequestParam(value = "quantidade", required = false) Long quantidade,
      @RequestParam(value = "id-cliente", required = false) Long id) {
    System.out.println("Buscando clientes, quantidade: " + quantidade);
    List<PedidoEntity> listaPedidos;
    // TODO gerar lógica de busca por id de cliente
    listaPedidos = pedidoService.buscarClientes();
    if (!(quantidade == null)) {
      listaPedidos = listaPedidos.stream().limit(quantidade).collect(Collectors.toList());
    } ;
    return new ResponseEntity<>(listaPedidos, HttpStatus.OK);
  }

  @GetMapping("/pedidos/{id}")
  public ResponseEntity<PedidoEntity> selecionar(@PathVariable(value = "id") Long id) {
    System.out.println("Buscando pedido id: " + id);
    Optional<PedidoEntity> resultado = pedidoService.buscarPorId(id);
    if (!resultado.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } ;
    return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
  }

  @DeleteMapping("/pedidos/{id}")
  public ResponseEntity<String> remover(@PathVariable(value = "id") Long id) {
    System.out.println("Solicitado deletar pedido id: " + id);
    if (!pedidoService.checkById(id)) {
      System.out.println("Pedido não existe, retornando 404");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } ;
    System.out.println("Deletando pedido");
    pedidoService.deletar(id);
    return new ResponseEntity<>("Pedido removido do BD", HttpStatus.NO_CONTENT);
  }

  @GetMapping("/pedidos/{id}/itens")
  public ResponseEntity<List<PedidoItemEntity>> selecionarItens(@PathVariable Long id) {
    System.out.println("Solicitados os itens do pedido id: " + id);
    Optional<PedidoEntity> resultado = pedidoService.buscarPorId(id);
    if (!resultado.isPresent()) {
      System.out.println("Pedido não encontrado");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } ;
    System.out.println("Lista retornada");
    return new ResponseEntity<>(resultado.get().getItens(), HttpStatus.OK);
  }
}
