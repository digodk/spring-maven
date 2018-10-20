package br.com.senior.treinamento.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senior.treinamento.demo.entidades.ClienteEntity;
import br.com.senior.treinamento.demo.entidades.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{

  List<PedidoEntity> findByCliente(ClienteEntity cliente);

}
