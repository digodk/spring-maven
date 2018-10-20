package br.com.senior.treinamento.demo.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @NotNull
  private String nome;
  
  @Column//(unique=true)
  @NotNull
  private String email;
  
  @OneToMany(mappedBy = "cliente")
  private List<PedidoEntity> pedidos;
  
  @Column(name = "DataCriado", nullable = false)
  private Date createdDate;

  public Date getCreatedDate() {
    return createdDate;
  }
  
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @PrePersist
  protected void prePersist() {
    if (this.createdDate == null)
      createdDate = new Date();
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<PedidoEntity> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<PedidoEntity> pedidos) {
    this.pedidos = pedidos;
  }
}
