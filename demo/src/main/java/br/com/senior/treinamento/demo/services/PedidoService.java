package br.com.senior.treinamento.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.respository.PedidoRepository;

@Service
public class PedidoService {

  private PedidoRepository pedidoRepository;

  public PedidoEntity salvar(PedidoEntity pedido) {
    return pedidoRepository.save(pedido);
  }

  public boolean checkById(long id) {
    return pedidoRepository.existsById(id);
  }

  public void deletar(Long id) {
    pedidoRepository.deleteById(id);
  }

  public void deletar(PedidoEntity pedido) {
    pedidoRepository.delete(pedido);
  }

  public Optional<PedidoEntity> buscarPorId(Long id) {
    return pedidoRepository.findById(id);
  }

  public List<PedidoEntity> buscarClientes() {
    return pedidoRepository.findAll();
  }

  public List<PedidoEntity> buscarPorCliente(ClienteEntity cliente) {
    return pedidoRepository.findByCliente(cliente);
  }

  public PedidoService(PedidoRepository pedidoRepository) {
    this.pedidoRepository = pedidoRepository;
  }
}
