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
    @Enumerated(EnumType.STRING)
    private ETimeType timeType;
    private String token;
    @Enumerated(EnumType.STRING)
    private EStatus statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_info_id")
    private DoctorInfor doctorInfor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patinet_id")
    private Patient Patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
