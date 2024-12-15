package org.example.osahaneatspringboot.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseEntity implements Serializable {

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    Date createAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    Date updateAt;

    @Column(name = "is_active")
    @Builder.Default
    Boolean isActive = true;
}
