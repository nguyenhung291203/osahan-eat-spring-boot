package org.example.osahaneatspringboot.entity;

import java.time.LocalTime;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true, length = 50)
    String title;

    String subtitle;

    @Column(nullable = false, unique = true)
    String address;

    String description;

    String image;
    Boolean isFreeShip;
    Boolean isActive;
    LocalTime openTime;
    LocalTime closeTime;

    Double latitude;

    Double longitude;
}
