package br.com.senior.treinamento.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.repository.ClienteRepository;

@Service
public class ClienteService {

  private ClienteRepository clienteRepository;

  public ClienteEntity salvar(ClienteEntity cliente) {
    return clienteRepository.save(cliente);
  }

  public boolean checkById(long id) {
    return clienteRepository.existsById(id);
  }

  public void deletar(Long id) {
    clienteRepository.deleteById(id);
  }

  public void deletar(ClienteEntity cliente) {
    clienteRepository.delete(cliente);
  }

  public Optional<ClienteEntity> buscarPorId(Long id) {
    return clienteRepository.findById(id);
  }

  public List<ClienteEntity> buscarClientes() {
    return clienteRepository.findAll();
  }
  
  /*public List<ClienteEntity> buscarPorEmail(String email) {
    return clienteRepository.findByEmailIgnoreCase(email);
  }*/

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

}
