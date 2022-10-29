package com.uit.bookingcare.service.doctorInfor.impl;

import com.uit.bookingcare.constant.enums.EUserType;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.doctor.DoctorInforDto;
import com.uit.bookingcare.mapper.doctor.DoctorInforMapper;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.service.doctorInfor.IFindAllUserDoctorInforService;
import com.uit.bookingcare.service.tuition.AbstractBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllUserDoctorInforServiceImpl extends AbstractBaseService<String, List<DoctorInforDto>> implements IFindAllUserDoctorInforService {

    private final DoctorInforMapper doctorInforMapper;
    private final UserRepository userRepository;

    @Override
    public List<DoctorInforDto> doing(String lastname) {
        List<User> result = userRepository.findAllByUserTypeAndLastnameContaining(EUserType.DOCTOR,lastname);
        return doctorInforMapper.toDoctorInforDtoList(result);
    }
}
