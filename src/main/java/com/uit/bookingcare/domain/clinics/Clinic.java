package com.uit.bookingcare.domain.clinics;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.speciatly.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clinics")
public class Clinic extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String address;
    private String image;
    @Column(name = "descriptionHTML", columnDefinition = "TEXT")
    private String descriptionHTML;
    @Column(name = "descriptionMarkdown", columnDefinition = "TEXT")
    private String descriptionMarkdown;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic")
    private Set<DoctorInfor> doctorInfors = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic")
    private Set<Specialty> specialties = new HashSet<>();

}
