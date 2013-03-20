package org.crash.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author <a href="mailto:alain.defrance@exoplatform.com">Alain Defrance</a>
 */
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, length = 30)
  @NotNull
  @Size(min = 1, max = 30)
  private String name;

  @Column(length = 3000)
  private String description;

  @ManyToOne
  private Category category;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
  List<Item> items;

  public Product() {
  }

  public Product(String name, String description, Category category) {
    this.name = name;
    this.description = description;
    this.category = category;
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

}
