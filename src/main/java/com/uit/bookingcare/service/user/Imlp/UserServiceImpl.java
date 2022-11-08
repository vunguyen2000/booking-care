package com.uit.bookingcare.service.user.Imlp;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.constant.enums.EStatus;
import com.uit.bookingcare.constant.enums.EUserType;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.user.UserDto;
import com.uit.bookingcare.mapper.doctor.UserMapper;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.clinic.CreateClinicRequest;
import com.uit.bookingcare.request.user.CreateUserRequest;
import com.uit.bookingcare.request.user.UpdateUserRequest;
import com.uit.bookingcare.service.exception.InvalidException;
import com.uit.bookingcare.service.exception.NotFoundException;
import com.uit.bookingcare.service.user.UserService;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.uit.bookingcare.constant.enums.ERoleType.ADMIN;
import static com.uit.bookingcare.constant.enums.EUserType.DOCTOR;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    private MessageHelper messageHelper;

    @Override
    public List<UserDto> findAll(String id) {

        if (id.equals("ALL")) {
            return userMapper.userDtoList(userRepository.findAll());
        } else {
            Optional<User> optionalEnity = userRepository.findById(Long.parseLong(id));
            List<User> intLIst = Arrays.asList(optionalEnity.get());
            return userMapper.userDtoList(intLIst);
        }
    }

    @Override
    public void save(CreateUserRequest request) {
        String pwdBcrypt = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt(10));
        request.setPassword(pwdBcrypt);
        User user = userRepository.findByEmail(request.getAddress()).orElse(null);
        if (user != null) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.EXIST));
        }
        userRepository.save(userMapper.toUser(request));
    }
    @Override
    public void update(UpdateUserRequest request) {
        if (request.getId() == null) {
            throw new InvalidException(messageHelper.getMessage(MessageCode.User.INVALID));
        }
        User oldUser = userRepository.findById(request.getId()).orElse(null);
        if (oldUser == null) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        userMapper.updateUser(request, oldUser);
        userRepository.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NotFoundException(messageHelper.getMessage(MessageCode.User.NOT_FOUND));
        }
        userRepository.deleteById(id);
    }
}

