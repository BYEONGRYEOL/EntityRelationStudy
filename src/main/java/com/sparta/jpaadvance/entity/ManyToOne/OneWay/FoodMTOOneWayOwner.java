package com.sparta.jpaadvance.entity.ManyToOne.OneWay;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "food_mto_oneway_owner")
public class FoodMTOOneWayOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_mto_oneway_dependent_id")
    private UserMTOOneWayDependent user;
}