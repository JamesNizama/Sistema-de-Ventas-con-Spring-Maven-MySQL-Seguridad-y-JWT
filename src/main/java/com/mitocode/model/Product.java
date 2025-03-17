package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProduct;

    @ManyToOne // FK
    @JoinColumn(nullable = false, name = "id_category", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private Category category;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "decimal(6,2)") //9999.99
    private double price;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false)
    private boolean enabled;
}
