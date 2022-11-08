package com.uit.bookingcare.service.auth;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.auth.UserPasswordDto;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.service.exception.InvalidException;
import com.uit.bookingcare.service.exception.NotFoundException;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final MessageHelper messageHelper;

    public Boolean changePassword(UserPasswordDto userPasswordDto) {
        User user = userRepository.findById(userPasswordDto.getUserId())
                .orElseThrow(()-> new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND)));
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        if (!b.matches(userPasswordDto.getOldPassword(), user.getPassword())){
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.WRONG_PASS));
        }
        String pwdBcrypt = BCrypt.hashpw(userPasswordDto.getNewPassword(), BCrypt.gensalt(10));
        user.setPassword(pwdBcrypt);
        userRepository.save(user);
        return true;
    }

}
