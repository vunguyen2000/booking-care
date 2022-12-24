package com.uit.bookingcare.service.booking.impl;

import com.uit.bookingcare.constant.MessageCode;
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
import com.uit.bookingcare.service.ClientService;
import com.uit.bookingcare.service.booking.BookingService;
import com.uit.bookingcare.service.exception.InvalidException;
import com.uit.bookingcare.service.sdi.ClientSdi;
import com.uit.bookingcare.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

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
    @Autowired
    private MessageHelper messageHelper;
    @Autowired
    private ClientService clientService;

    @Override
    public void update(String token) {
        Booking oldBooking = bookingRepository.findByTokenContainingIgnoreCase(token).orElse(null);
        if (oldBooking != null) {
            oldBooking.setStatusId(EStatus.S2);
            bookingRepository.save(oldBooking);
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void create(PostBookAppointment request) {
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user != null) {
            Booking booking = bookingRepository.findByPatientIdAndScheduleId(user.getId(), request.getScheduleId()).orElse(null);
            if (booking != null) {
                throw new InvalidException(messageHelper.getMessage(MessageCode.Booking.IS_EXIST));
            }else {
                request.setStatusId(EStatus.S1);
                request.setPatientId(user.getId());
            }
        }
        else {
            User newUser = userRepository.saveAndFlush(userMapper.createNewPatient(request));
            request.setStatusId(EStatus.S1);
            request.setPatientId(newUser.getId());
        }
        Booking booking = bookingMapper.createNewBooking(request);
        String token = UUID.randomUUID().toString();
        booking.setToken(token);
        bookingRepository.save(booking);
        ClientSdi client = new ClientSdi();
        client.setEmail(request.getEmail());
        client.setToken(token);
        client.setName(request.getFullName());
        client.setAddress(request.getAddress());
        clientService.create(client);
    }
    @Override
    public void sendRemedy(SendRemedyDto request) {
        Booking oldBooking = bookingRepository.findByTokenContainingIgnoreCase(request.getToken()).orElse(null);
        if (oldBooking != null) {
            oldBooking.setStatusId(EStatus.S3);
            bookingRepository.save(oldBooking);
        }

    }
//    @Override
//    public void verify(VerifyBookAppointmentDto request) {
//        Booking oldBooking = bookingRepository.findByTokenContainingIgnoreCase(request.getToken()).orElse(null);
//        if (oldBooking != null) {
//            oldBooking.setStatusId(EStatus.S2);
//            bookingRepository.save(oldBooking);
//        }
//    }
}
