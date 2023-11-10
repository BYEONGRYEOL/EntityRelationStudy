package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.repository.FoodMTOOneWayOwnerRepository;
import com.sparta.jpaadvance.repository.UserMTOOneWayDependentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ManyToOneOneWayTest {

    @Autowired
    UserMTOOneWayDependentRepository userRepository;
    @Autowired
    FoodMTOOneWayOwnerRepository foodRepository;


}