package br.com.senior.treinamento.demo.respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
  
  public List<ClienteEntity> findByEmailIgnoreCase(String email);
}
