package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.FoodOneToOneOwner;
import com.sparta.jpaadvance.entity.UserOneToOneDependent;
import com.sparta.jpaadvance.repository.FoodOneToOneOwnerRepository;
import com.sparta.jpaadvance.repository.UserOneToOneDependentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Transactional
@SpringBootTest
public class OneToOneTest {

    private final String FOOD_NAME = "foodName";
    private final String USER_NAME = "username";

    @Autowired
    FoodOneToOneOwnerRepository foodRepository;
    @Autowired
    UserOneToOneDependentRepository userRepository;

    @Test
    void insertNullValueIntoNonNullColumnTest() {
        FoodOneToOneOwner hasNullNameFood = new FoodOneToOneOwner();
        hasNullNameFood.setPrice(1234);
        assertThatThrownBy(()->foodRepository.save(hasNullNameFood))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("not-null property references a null or transient value");
    }


    @Test
    @Rollback(value = false) // 테스트에서는 @Transactional 에 의해 자동 rollback 됨으로 false 설정해준다.
    @DisplayName("1대1 단방향 외래키 저장 성공 테스트")
    void oneToOneForiegnKeySuccessTest() {

        UserOneToOneDependent user = getUser();

        // 외래 키의 주인인 Food Entity user 필드에 user 객체를 추가해 줍니다.
        FoodOneToOneOwner food = new FoodOneToOneOwner();
        food.setName("후라이드 치킨");
        food.setPrice(15000);
        food.setUserOneToOneDependent(user); // 외래 키(연관 관계) 설정

        // 어차피 save에는 Transactional 어노테이션이 달려있다.
        userRepository.save(user);
        foodRepository.save(food);
    }


    FoodOneToOneOwner getFood() {
        return new FoodOneToOneOwner(FOOD_NAME, 100);
    }
    UserOneToOneDependent getUser(){
        return new UserOneToOneDependent(USER_NAME);
    }
}

