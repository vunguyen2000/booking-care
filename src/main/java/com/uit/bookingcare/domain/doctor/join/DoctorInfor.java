package com.uit.bookingcare.domain.doctor.join;

import com.uit.bookingcare.constant.enums.*;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.history.Histories;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.speciatly.Specialty;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.utils.ECalendarShiftConverter;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "description_html", columnDefinition = "TEXT")
    private String descriptionHTML;
    @Column(name = "description_markdown", columnDefinition = "TEXT")
    private String descriptionMarkdown;

    @Convert(converter = ECalendarShiftConverter.class)
    private List<ETimeType> calendarShifts;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    private User user;
    @Column(columnDefinition = "TEXT")
    private String note;

    private Integer count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorInfor")
    private Set<Histories> histories = new HashSet<>();

}
