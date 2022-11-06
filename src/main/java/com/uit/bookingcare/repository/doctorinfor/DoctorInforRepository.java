package com.uit.bookingcare.repository.doctorinfor;


import com.uit.bookingcare.domain.doctor.DoctorInfor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorInforRepository extends JpaRepository<DoctorInfor, Long> {
    List<DoctorInfor> findAllByUserFirstNameContainingIgnoreCase(String name);

}
