package org.example.osahaneatspringboot.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Profile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, length = 100)
    String fullName;

    boolean gender;
    String avatar;

    @Column(name = "data_of_birth")
    LocalDate dateOfBirth;

    String address;

    @Column(unique = true)
    String email;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    Account account;
}
