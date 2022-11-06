package com.uit.bookingcare.domain.schedule;

import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.patient.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "schedule")
public class Schedule extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long currentnumber;
    private long maxNumber;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private ETimeType timeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorInfor doctorInfor;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private Set<Booking> bookings = new HashSet<>();

}
