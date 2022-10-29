package com.uit.bookingcare.domain.schedule;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.patient.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
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
    private Date date;
    private Time timeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private DoctorInfor doctorInfor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "schedule")
    private Set<Booking> bookings = new HashSet<>();

}
