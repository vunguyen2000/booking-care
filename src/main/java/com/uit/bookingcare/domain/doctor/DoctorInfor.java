package com.uit.bookingcare.domain.doctor;

import com.uit.bookingcare.constant.enums.*;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.clinics.join.ClinicSpecialty;
import com.uit.bookingcare.domain.history.Histories;
import com.uit.bookingcare.domain.schedule.Schedule;
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
@Table(name = "doctor_infor")
public class DoctorInfor extends SqlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private EPayment payment;
    @Enumerated(EnumType.STRING)
    private EPrice price;
    @Enumerated(EnumType.STRING)
    private EPosition position;
    @Enumerated(EnumType.STRING)
    private EProvince province;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "content_markdown", columnDefinition = "TEXT")
    private String contentMarkdown;
    @Column(name = "content_html", columnDefinition = "TEXT")
    private String contentHTML;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @Column(columnDefinition = "TEXT")
    private String note;

    private Integer count;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Histories> histories = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private List<ClinicSpecialty> clinicSpecialties = new ArrayList<>();

    public void setClinicSpecialties(List<ClinicSpecialty> clinicSpecialties) {
        this.getClinicSpecialties().clear();
        this.clinicSpecialties = clinicSpecialties;
    }

    public void setClinicSpecialties(ClinicSpecialty clinicSpecialty) {
        this.setClinicSpecialties(List.of(clinicSpecialty));
    }
}
