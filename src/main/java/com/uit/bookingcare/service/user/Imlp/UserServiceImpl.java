package com.uit.bookingcare.service.user.Imlp;

import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.user.UserDto;
import com.uit.bookingcare.mapper.doctor.UserMapper;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.user.CreateUserRequest;
import com.uit.bookingcare.request.user.UpdateUserRequest;
import com.uit.bookingcare.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll(String id) {

        if (id.equals("ALL")){
            return userMapper.userDtoList(userRepository.findAll());
        }
        else {
            Optional<User> optionalEnity = userRepository.findById(Long.parseLong(id));
            List<User> intLIst = Arrays.asList(optionalEnity.get());
            return  userMapper.userDtoList(intLIst);
        }
    }

    @Override
    public void save(CreateUserRequest request) {
        userRepository.save(userMapper.toUser(request));
    }

    @Override
    public void update(UpdateUserRequest request) {
        if (request.getId() == null) {
            return;
        }
        User oldUser = userRepository.findById(request.getId()).orElse(null);
        if (oldUser == null) {
            return;
        }
        userMapper.updateUser(request, oldUser);
        userRepository.save(oldUser);
    }

}

