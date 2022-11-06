package com.uit.bookingcare.service.auth;

import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final MessageHelper messageHelper;

}
