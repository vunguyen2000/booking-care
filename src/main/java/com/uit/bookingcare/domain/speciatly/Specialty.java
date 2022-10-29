package com.uit.bookingcare.domain.speciatly;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.markdown.Markdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "specialty")
public class Specialty extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String image;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialty")
    private List<DoctorInfor> doctorInfor = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @OneToOne(
            mappedBy = "specialty",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Markdown markdown;

}
