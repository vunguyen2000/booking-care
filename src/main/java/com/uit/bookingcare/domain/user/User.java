package com.uit.bookingcare.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uit.bookingcare.constant.enums.EGender;
import com.uit.bookingcare.constant.enums.EUserType;
import com.uit.bookingcare.domain.SqlEntity;
import com.uit.bookingcare.domain.doctor.join.DoctorInfor;
import com.uit.bookingcare.domain.patient.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User extends SqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String firstname;
    @Column(unique = true)
    private String lastname;
    private String address;
    private String image;
    private String fullName;
    private String phonenumber;

    @Enumerated(EnumType.STRING)
    private EUserType userType;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public User(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Patient patient;

    public void setPatient(Patient patient) {
        if (patient == null) {
            if (this.patient != null) {
                this.patient.setUser(null);
            }
        } else {
            doctorInfor.setUser(this);
        }
        this.doctorInfor = doctorInfor;
    }
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private DoctorInfor doctorInfor;

    public void setDoctor(DoctorInfor doctorInfor) {
        if (patient == null) {
            if (this.doctorInfor != null) {
                this.doctorInfor.setUser(null);
            }
        } else {
            patient.setUser(this);
        }
        this.patient = patient;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}


