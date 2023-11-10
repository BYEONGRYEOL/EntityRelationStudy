package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.OneToOne.TwoWay.FoodOTOTwoWayOwner;
import com.sparta.jpaadvance.entity.OneToOne.TwoWay.UserOTOTwoWayDependent;
import com.sparta.jpaadvance.repository.FoodOTOTwoWayOwnerRepository;
import com.sparta.jpaadvance.repository.UserOTOTwoWayDependentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class OneToOneTwoWayTest {

    private final String FOOD_NAME = "foodName";
    private final String USER_NAME = "username";

    @Autowired
    FoodOTOTwoWayOwnerRepository foodRepository;
    @Autowired
    UserOTOTwoWayDependentRepository userRepository;




    @Test
    @Rollback(value = false)
    @DisplayName("1대1 양방향 테스트 : 주인이 아닌 엔티티에서 저장시 외래키 저장 안됨")
    void savedByDependentOnlyTest() {
        FoodOTOTwoWayOwner food = getFood();

        // 외래 키의 주인이 아닌 User 에서 Food 를 저장해보겠습니다.
        UserOTOTwoWayDependent user = getUser();
        user.setFood(food);

        userRepository.save(user);
        foodRepository.save(food);

        // 확인해 보시면 user_id 값이 들어가 있지 않은 것을 확인하실 수 있습니다.

    }

    @Test
    @Rollback(value = false)
    @DisplayName("1대1 양방향 테스트 : 외래 키 저장 실패 -> 성공")
    void savedByDependentAndOwnerTest() {
        FoodOTOTwoWayOwner food = getFood();

        UserOTOTwoWayDependent user = getUser();
        // 직접 작성한 addFood 함수
        user.addFood(food);

        userRepository.save(user);
        foodRepository.save(food);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("1대1 양방향 테스트 주인이 dependent를 저장")
    void savedByOwnerTest() {
        FoodOTOTwoWayOwner food = getFood();
        UserOTOTwoWayDependent user = getUser();

        food.setUser(user); // 외래 키(연관 관계) 설정

        userRepository.save(user);
        foodRepository.save(food);
    }


    FoodOTOTwoWayOwner getFood() {
        return new FoodOTOTwoWayOwner(FOOD_NAME, 100);
    }
    UserOTOTwoWayDependent getUser(){
        return new UserOTOTwoWayDependent(USER_NAME);
    }
}
