package com.uit.bookingcare.domain.clinics;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.clinics.join.ClinicSpecialty;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
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
    @Column( columnDefinition = "TEXT")
    private String image;
    @Column(name = "description_html", columnDefinition = "TEXT")
    private String descriptionHTML;
    @Column(name = "description_markdown", columnDefinition = "TEXT")
    private String descriptionMarkdown;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clinic")
    private Set<ClinicSpecialty> clinicSpecialties = new HashSet<>();

}
