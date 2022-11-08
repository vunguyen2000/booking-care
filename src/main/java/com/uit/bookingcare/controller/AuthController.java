package com.uit.bookingcare.controller;
import com.uit.bookingcare.configuration.JwtTokenUtil;
import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.dto.UserPrincipal;
import com.uit.bookingcare.dto.auth.UserLoginDto;
import com.uit.bookingcare.dto.auth.UserPasswordDto;
import com.uit.bookingcare.dto.response.ApiResponse;
import com.uit.bookingcare.service.auth.AuthService;
import com.uit.bookingcare.service.auth.JwtUserDetailsService;
import com.uit.bookingcare.service.exception.ForbiddenException;
import com.uit.bookingcare.utils.MessageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@Api(value = "Auth APIs")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final MessageHelper messageHelper;

    private final AuthService authService;

    @ApiOperation(value = "Login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginDto authenticationRequest){

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        JSONObject json = new JSONObject();
        json.put("access_token", token);
        log.info("Auth Controller: put access token");
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());
    }

    private void authenticate(String username, String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new ForbiddenException(messageHelper.getMessage(MessageCode.User.WRONG,username));
        }
    }

    @ApiOperation(value = "Update password", authorizations = {@Authorization(value = "JWT")})
    @PutMapping(value = "/update-password")
    public ResponseEntity<?> updatePasswordUserDoctor(@RequestBody UserPasswordDto userPasswordDto) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userPasswordDto.setUserId(userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(authService.changePassword(userPasswordDto)));
    }
}

