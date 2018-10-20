package br.com.senior.treinamento.demo.entidades;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class PedidoEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column
  @NotNull
  private LocalDateTime data;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private ClienteEntity cliente;
  
  @OneToMany(mappedBy = "pedido")
  private List<PedidoItemEntity> itens;
  
  @Column(name = "DataCriado", nullable = false)
  private Date createdDate;

  public Date getCreatedDate() {
    return createdDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getData() {
    return data;
  }

  public void setData(LocalDateTime data) {
    this.data = data;
  }

  public ClienteEntity getCliente() {
    return cliente;
  }

  public void setCliente(ClienteEntity cliente) {
    this.cliente = cliente;
  }
  
  @PrePersist
  protected void prePersist() {
    if (this.createdDate == null)
      createdDate = new Date();
  }

  public List<PedidoItemEntity> getItens() {
    return itens;
  }

  public void setItens(List<PedidoItemEntity> itens) {
    this.itens = itens;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
}
