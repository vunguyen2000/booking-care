package com.uit.bookingcare.domain.doctor.join;


import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.speciatly.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Table(name = "doctor_clinic_specialty")
public class DoctorClinicSpecialty {
    private long id;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorClinicSpecialty")
//    private List<DoctorInfor> doctorInfors = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorClinicSpecialty")
//    private List<Clinic> clinics = new ArrayList<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctorClinicSpecialty")
//    private List<Specialty> specialties = new ArrayList<>();



}
