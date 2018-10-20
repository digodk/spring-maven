package br.com.senior.treinamento.demo.respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.senior.treinamento.demo.entidades.PedidoEntity;
import br.com.senior.treinamento.demo.entidades.PedidoItemEntity;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItemEntity, Long>{

  List<PedidoItemEntity> findByPedido(PedidoEntity pedido);

}
