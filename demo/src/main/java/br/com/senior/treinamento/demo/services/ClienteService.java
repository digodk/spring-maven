package br.com.senior.treinamento.demo.services;

import org.springframework.stereotype.Service;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.respository.ClienteRepository;

@Service
public class ClienteService {

  private ClienteRepository clienteRepository;
  
  public ClienteEntity salvar(ClienteEntity cliente) {
    return clienteRepository.save(cliente);
  }

  public void deletar(Long id) {
    // TODO Auto-generated method stub
    
  }

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

}
