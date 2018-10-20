package br.com.senior.treinamento.demo.rest;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;
import br.com.senior.treinamento.demo.services.PedidoItemService;

@RestController
@RequestMapping("/api")
public class PedidoItemController {
  private PedidoItemService pedidoItemService;

  public PedidoItemController(PedidoItemService pedidoItemService) {
    this.pedidoItemService = pedidoItemService;
  }

  @PostMapping("/itens")
  public ResponseEntity<PedidoItemEntity> criar(@RequestBody PedidoItemEntity pedido) {
    System.out.println("Criando um novo item");

    return new ResponseEntity<>(pedidoItemService.salvar(pedido), HttpStatus.CREATED);
  }

  @GetMapping("/itens/{id}")
  public ResponseEntity<PedidoItemEntity> selecionar(@PathVariable(value = "id") Long id) {
    Optional<PedidoItemEntity> resultado = pedidoItemService.buscarPorId(id);
    if (!resultado.isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(resultado.get(), HttpStatus.OK);
  }
}
