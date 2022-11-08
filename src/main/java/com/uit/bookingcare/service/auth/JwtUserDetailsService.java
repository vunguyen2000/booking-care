package com.uit.bookingcare.service.auth;

import com.uit.bookingcare.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.uit.bookingcare.domain.user.User user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
