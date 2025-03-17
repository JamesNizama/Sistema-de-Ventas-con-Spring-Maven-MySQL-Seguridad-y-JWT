package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSaleDetail;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_sale", foreignKey = @ForeignKey(name = "FK_SALEDETAIL_SALE"))
    private Sale sale;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_product", foreignKey = @ForeignKey(name = "FK_SALEDETAIL_PRODUCT"))
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private double salePrice;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private double discount;


}
