package com.sparta.jpaadvance.repository;

import com.sparta.jpaadvance.entity.UserOneToOneDependent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOneToOneDependentRepository extends JpaRepository<UserOneToOneDependent, Long> {
}
