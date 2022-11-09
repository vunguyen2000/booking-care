package com.uit.bookingcare.service.tuition;

import com.uit.bookingcare.constant.enums.ERoleType;
import com.uit.bookingcare.dto.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public boolean hasRole(String eRoleType) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userPrincipal.getRoleCode().equals(ERoleType.valueOf(eRoleType));
    }
}
