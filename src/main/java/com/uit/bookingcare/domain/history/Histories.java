package com.uit.bookingcare.domain.history;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.patient.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "histories")
public class Histories extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String files;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private DoctorInfor doctorInfor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


}
