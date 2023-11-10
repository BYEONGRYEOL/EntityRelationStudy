package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.ManyToOne.OneWay.FoodMTOOneWayOwner;
import com.sparta.jpaadvance.entity.ManyToOne.OneWay.UserMTOOneWayDependent;
import com.sparta.jpaadvance.entity.ManyToOne.TwoWay.FoodMTOTwoWayOwner;
import com.sparta.jpaadvance.entity.ManyToOne.TwoWay.UserMTOTwoWayDependent;
import com.sparta.jpaadvance.repository.FoodMTOOneWayOwnerRepository;
import com.sparta.jpaadvance.repository.FoodMTOTwoWayOwnerRepository;
import com.sparta.jpaadvance.repository.UserMTOOneWayDependentRepository;
import com.sparta.jpaadvance.repository.UserMTOTwoWayDependentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class ManyToOneTwoWayTest {

    private final String FOOD_NAME = "foodName";
    private final String USER_NAME = "username";
    @Autowired
    UserMTOTwoWayDependentRepository userRepository;
    @Autowired
    FoodMTOTwoWayOwnerRepository foodRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("N대1 양방향 테스트 : 외래 키 저장 실패")
    void test2() {

        FoodMTOTwoWayOwner food = getFood();

        FoodMTOTwoWayOwner food2 = getFood();

        // 외래 키의 주인이 아닌 User 에서 Food 를 저장해보겠습니다.
        UserMTOTwoWayDependent user = getUser();

        user.getFoodList().add(food);
        user.getFoodList().add(food2);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);

        // 확인해 보시면 user_id 값이 들어가 있지 않은 것을 확인하실 수 있습니다.
    }

    @Test
    @Rollback(value = false)
    @DisplayName("N대1 양방향 테스트 : 외래 키 저장 실패 -> 성공")
    void test3() {

        FoodMTOTwoWayOwner food = getFood();
        FoodMTOTwoWayOwner food2 = getFood();

        // 외래 키의 주인이 아닌 User 에서 Food 를 쉽게 저장하기 위해 addFoodList() 메서드 생성하고
        // 해당 메서드에 외래 키(연관 관계) 설정 food.setUser(this); 추가
        UserMTOTwoWayDependent user = getUser();
        user.addFoodList(food);
        user.addFoodList(food2);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("N대1 양방향 테스트")
    void test4() {
        UserMTOTwoWayDependent user = getUser();


        FoodMTOTwoWayOwner food = getFood();
        FoodMTOTwoWayOwner food2 = getFood();

        food.setUser(user); // 외래 키(연관 관계) 설정
        food2.setUser(user); // 외래 키(연관 관계) 설정

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);
    }

    @Test
    @DisplayName("N대1 조회 : Food 기준 user 정보 조회")
    void test5() {
        FoodMTOTwoWayOwner food = foodRepository.findById(1L).orElseThrow(NullPointerException::new);
        // 음식 정보 조회
        System.out.println("food.getName() = " + food.getName());

        // 음식을 주문한 고객 정보 조회
        System.out.println("food.getUser().getName() = " + food.getUser().getName());
    }

    @Test
    @DisplayName("N대1 조회 : User 기준 food 정보 조회")
    void test6() {
        UserMTOTwoWayDependent user = userRepository.findById(1L).orElseThrow(NullPointerException::new);
        // 고객 정보 조회
        System.out.println("user.getName() = " + user.getName());

        // 해당 고객이 주문한 음식 정보 조회
        List<FoodMTOTwoWayOwner> foodList = user.getFoodList();
        for (FoodMTOTwoWayOwner food : foodList) {
            System.out.println("food.getName() = " + food.getName());
            System.out.println("food.getPrice() = " + food.getPrice());
        }
    }


    FoodMTOTwoWayOwner getFood() {
        return new FoodMTOTwoWayOwner(FOOD_NAME, 100);
    }
    UserMTOTwoWayDependent getUser(){
        return new UserMTOTwoWayDependent(USER_NAME);
    }
}