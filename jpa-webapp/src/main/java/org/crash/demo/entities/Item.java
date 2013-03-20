package org.crash.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 */
@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 30)
  @NotNull
  @Size(min = 1, max = 30)
  private String name;

  @Column(length = 3000)
  private String description;

  @Column(nullable = false)
  private Float unitCost;

  @ManyToOne
  private Product product;

  public Item() {
  }

  public Item(String name, String description, Float unitCost, Product product) {
    this.name = name;
    this.description = description;
    this.unitCost = unitCost;
    this.product = product;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Float getUnitCost() {
    return unitCost;
  }

  public void setUnitCost(Float unitCost) {
    this.unitCost = unitCost;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

}
