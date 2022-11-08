package com.uit.bookingcare.configuration;

import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class JpaAuditorAware implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        UserPrincipal principal;
        try {
            principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            principal=null;
            log.info("User not login");
        }
        if (principal!=null) {
            return Optional.of(new User(principal.getId()));
        } else {
            return Optional.empty();
        }
    }
}
