package com.uit.bookingcare.domain.markdown;

import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name ="markdown")
public class Markdown extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String contentHTML;
    private String contentMarkdown;
    private String descriptionMarkdown;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Clinic clinic;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private DoctorInfor doctorInfor;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Specialty specialty;


}
