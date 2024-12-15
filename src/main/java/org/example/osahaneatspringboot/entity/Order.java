package org.example.osahaneatspringboot.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;

    BigDecimal price;

    String status;
    String address;

    @Column(name = "phone_number", length = 10, nullable = false)
    String phoneNumber;

    @Column(name = "full_name", length = 255, nullable = false)
    String fullName;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails;
}
