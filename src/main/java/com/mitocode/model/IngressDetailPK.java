package com.mitocode.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class IngressDetailPK implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_ingress", foreignKey = @ForeignKey(name = "FK_INGRESSDETAILPK_INGRESS"))
    private Ingress ingress;

    @ManyToOne
    @JoinColumn(nullable = false, name = "id_product", foreignKey = @ForeignKey(name = "PK_INGRESSDETAILPK_PRODUCT"))
    private Product product;

}
