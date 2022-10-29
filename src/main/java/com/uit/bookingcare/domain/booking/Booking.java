package com.uit.bookingcare.domain.booking;

import com.uit.bookingcare.constant.enums.EStatus;
import com.uit.bookingcare.constant.enums.ETimeType;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.patient.Patient;
import com.uit.bookingcare.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name ="booking")
public class Booking extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "timeType")
    @Enumerated(EnumType.STRING)
    private ETimeType timeType;
    @Column(name = "token")
    private String token;
    @Column(name = "statusId")
    @Enumerated(EnumType.STRING)
    private EStatus statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private DoctorInfor doctorInfor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;
}
