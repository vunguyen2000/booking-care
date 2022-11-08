package com.uit.bookingcare.service.booking.impl;

import com.uit.bookingcare.constant.enums.EStatus;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.mapper.booking.BookingMapper;
import com.uit.bookingcare.mapper.doctor.UserMapper;
import com.uit.bookingcare.mapper.patient.PatientMapper;
import com.uit.bookingcare.repository.booking.BookingRepository;
import com.uit.bookingcare.repository.doctorinfor.DoctorInforRepository;
import com.uit.bookingcare.repository.patient.PatientRepository;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.booking.PostBookAppointment;
import com.uit.bookingcare.request.booking.SendRemedyDto;
import com.uit.bookingcare.request.booking.VerifyBookAppointmentDto;
import com.uit.bookingcare.service.booking.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final DoctorInforRepository doctorInforRepository;
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    @Override
    public void update(VerifyBookAppointmentDto request) {
        Booking oldBooking = bookingRepository.findByTokenContainingIgnoreCase(request.getToken());
        if(oldBooking!=null)
        {
            oldBooking.setStatusId(EStatus.S2);
            bookingRepository.save(oldBooking);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(PostBookAppointment request) {
        if(request.getScheduleId()!=null&request.getEmail()!=null) {
            User newUser = userRepository.saveAndFlush(userMapper.createNewPatient(request));
            request.setStatusId(EStatus.S1);
            request.setPatientId(newUser.getId());
            bookingRepository.save(bookingMapper.createNewBooking(request));
        }
    }

    @Override
    public void sendRemedy(SendRemedyDto request) {
        Booking oldBooking = bookingRepository.findByTokenContainingIgnoreCase(request.getToken());
        if(oldBooking!=null)
        {
            oldBooking.setStatusId(EStatus.S3);
            bookingRepository.save(oldBooking);
        }
    }
}
