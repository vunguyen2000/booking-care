package com.uit.bookingcare.domain.clinics;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.doctor.join.DoctorClinicSpecialty;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.markdown.Markdown;
import com.uit.bookingcare.domain.patient.Patient;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clinic")
public class Clinic extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String address;
    private String image;
    private String descriptionHTML;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic")
    private Set<DoctorInfor> doctorInfors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic")
    private Set<Specialty> specialties = new HashSet<>();

    @OneToOne(
            mappedBy = "clinic",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Markdown markdown;
}
