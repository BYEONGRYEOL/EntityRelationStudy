package com.sparta.jpaadvance.repository;

import com.sparta.jpaadvance.entity.FoodOneToOneOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FoodOneToOneOwnerRepository extends JpaRepository<FoodOneToOneOwner, Long> {
    public Optional<FoodOneToOneOwner> findByName(String name);
}
