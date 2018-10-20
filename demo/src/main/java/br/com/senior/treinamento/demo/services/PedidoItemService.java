package br.com.senior.treinamento.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;
import br.com.senior.treinamento.demo.respository.PedidoItemRepository;

@Service
public class PedidoItemService {

  private PedidoItemRepository pedidoItemRepository;

  public PedidoItemEntity salvar(PedidoItemEntity pedido) {
    return pedidoItemRepository.save(pedido);
  }

  public boolean checkById(long id) {
    return pedidoItemRepository.existsById(id);
  }

  public void deletar(Long id) {
    pedidoItemRepository.deleteById(id);
  }

  public void deletar(PedidoItemEntity pedido) {
    pedidoItemRepository.delete(pedido);
  }

  public Optional<PedidoItemEntity> buscarPorId(Long id) {
    return pedidoItemRepository.findById(id);
  }

  public List<PedidoItemEntity> buscarClientes() {
    return pedidoItemRepository.findAll();
  }

  public List<PedidoItemEntity> buscarPorCliente(ClienteEntity cliente) {
    //TODO gerar lógica de implementação
    return new ArrayList<>();
  }
  
  public List<PedidoItemEntity> buscarPorPedido(PedidoEntity pedido) {
    return pedidoItemRepository.findByPedido(pedido);
  }

  public PedidoItemService(PedidoItemRepository pedidoItemRepository) {
    this.pedidoItemRepository = pedidoItemRepository;
  }
}
