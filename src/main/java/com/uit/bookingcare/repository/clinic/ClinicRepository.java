package com.uit.bookingcare.repository.clinic;

import com.uit.bookingcare.domain.clinics.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {


    Optional<Clinic> findById(Long id);
    List<Clinic> findAllByNameContainingIgnoreCase(String name);
    List<Clinic> findAllByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String name, String address);

//    @Query(value = "SELECT * \n" +
//            " FROM class_room cr, open_course oc" +
//            " where cr.id = oc.class_id" +
//            " AND oc.semester_id = :semesterId" +
//            " AND cr.id = :classId",
//            nativeQuery = true)
//    Optional<Clinic> findClassRoomBySemesterId(Long clinicid, Long semesterId);
}
