package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@Table(name = "food")
@NoArgsConstructor
public class FoodOneToOneOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private double price;

    @OneToOne
    @JoinColumn(name = "userOneToOneDependent_id")
    private UserOneToOneDependent userOneToOneDependent;

    public FoodOneToOneOwner(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

