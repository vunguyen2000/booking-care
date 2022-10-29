package com.uit.bookingcare.repository.user;

import com.uit.bookingcare.constant.enums.EUserType;
import com.uit.bookingcare.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUserTypeAndLastNameContaining(EUserType userType, String lastName);

}


