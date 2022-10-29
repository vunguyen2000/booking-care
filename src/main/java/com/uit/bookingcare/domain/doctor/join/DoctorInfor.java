package com.uit.bookingcare.domain.doctor.join;

import com.uit.bookingcare.constant.enums.ECalendarShift;
import com.uit.bookingcare.constant.enums.EPayment;
import com.uit.bookingcare.constant.enums.EPosition;
import com.uit.bookingcare.constant.enums.EPrice;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.history.Histories;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.utils.ECalendarShiftConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor_infor")
public class DoctorInfor extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private EPayment payment;
    @Enumerated(EnumType.STRING)
    private EPrice price;
    @Enumerated(EnumType.STRING)
    private EPosition position;
    @Convert(converter = ECalendarShiftConverter.class)
    private List<ECalendarShift> calendarShifts;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @Column(columnDefinition = "TEXT")
    private String note;

    private String count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinicId")
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialtyId")
    private Specialty specialty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Histories> histories = new HashSet<>();


}
