package com.uit.bookingcare.domain.booking;

import com.uit.bookingcare.constant.enums.EStatus;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.patient.Patient;
import com.uit.bookingcare.domain.schedule.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="booking")
public class Booking extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "token")
    private String token;
    @Column(name = "status_id")
    @Enumerated(EnumType.STRING)
    private EStatus statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
