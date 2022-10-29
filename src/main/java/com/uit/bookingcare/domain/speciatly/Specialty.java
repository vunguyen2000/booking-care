package com.uit.bookingcare.domain.speciatly;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "specialties")
public class Specialty extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @Column( columnDefinition = "TEXT")
    private String image;

    @Column(name = "description_html", columnDefinition = "TEXT")
    private String descriptionHTML;
    @Column(name = "description_markdown", columnDefinition = "TEXT")
    private String descriptionMarkdown;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialty")
    private List<DoctorInfor> doctorInfor = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;



}
