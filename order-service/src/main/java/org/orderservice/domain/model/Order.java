package org.orderservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id")
    private String orderId;
    @Column(name ="customer_id")
    private int customerId;
    @Column(name ="amount")
    private Double amount;
    @Column(name ="status")
    private String status;
}
