package com.mitocode.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(IngressDetailPK.class)
public class IngressDetail {

    @Id
    private Ingress ingress;

    @Id
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(nullable = false, columnDefinition = "decimal(6,2)")
    private double cost;

//    static class IngressDetailPK {
//        @ManyToOne
//        private Ingress ingress;
//
//        @ManyToOne
//        private Product product;
//    }
//

}
