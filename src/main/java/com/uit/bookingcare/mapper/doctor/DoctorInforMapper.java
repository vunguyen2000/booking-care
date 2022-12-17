package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.constant.enums.EPayment;
import com.uit.bookingcare.constant.enums.EPrice;
import com.uit.bookingcare.constant.enums.EProvince;
import com.uit.bookingcare.domain.clinics.Clinic;
import com.uit.bookingcare.domain.clinics.join.ClinicSpecialty;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.dto.doctor.*;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.clinic.ClinicSpecialtyRepository;
import com.uit.bookingcare.repository.doctorinfor.DoctorInforRepository;
import com.uit.bookingcare.repository.specialty.SpecialtyRepository;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.doctor.BulkCreateSchedule;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class DoctorInforMapper implements MapperBase {

    @Autowired
    private  SpecialtyRepository specialtyRepository;
    @Autowired
    private ClinicRepository clinicRepository;


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorInforRepository doctorInforRepository;

    @Autowired
    private ClinicSpecialtyRepository clinicSpecialtyRepository;

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.address", target = "address")
    @Mapping(source = "user.phonenumber", target = "phonenumber")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.image", target = "image")
    @Mapping(source = "user.gender", target = "gender")
    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "position", target = "positionId")
    public abstract DoctorInforDto doctorInforDto(DoctorInfor doctorInfor);
    public abstract List<DoctorInforDto> doctorInforDtoList(List<DoctorInfor> doctorInfors);


    @Named("toExtraDoctorInforDto")
    @BeforeMapping
    protected void toExtraDoctorInforDto(DoctorInfor doctorInfor, @MappingTarget DoctorExtraDto dto) {
        EPayment payment = doctorInfor.getPayment();
        EProvince province = doctorInfor.getProvince();
        EPrice price = doctorInfor.getPrice();
        dto.setPriceTypeData(new PriceTypeDataDto(price.getValueEn(), price.getValueVi()));
        dto.setPaymentTypeData(new PaymentTypeDataDto(payment.getValueEn(), payment.getValueVi()));
        dto.setProvinceTypeData(new ProvinceTypeDataDto(province.getValueEn(), province.getValueVi()));

        ClinicSpecialty c = clinicSpecialtyRepository.findFirstByDoctorInforId(doctorInfor.getId()).orElse(null);
        if (c !=null){
            dto.setSpecialtyId(c.getSpecialty().getId());
            dto.setClinicId(c.getClinic().getId());
            Clinic clinic = clinicRepository.findById(dto.getClinicId()).orElse(new Clinic());
            dto.setNameClinic(clinic.getName());
            dto.setAddressClinic(clinic.getAddress());
        }
    }
    @BeanMapping(qualifiedByName = "toExtraDoctorInforDto" ,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "price", target = "priceId")
    @Mapping(source = "province", target = "provinceId")
    @Mapping(source = "payment", target = "paymentId")
    @Mapping(source = "count", target = "count")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "user.image", target = "image")
    public abstract DoctorExtraDto toExtraDoctorInforDto(DoctorInfor doctorInfor);

    @Named("updateDoctorInfor")
    @BeforeMapping
    protected void updateDoctorInforBefore(UpdateDoctorInforRequest dto, @MappingTarget DoctorInfor entity) {
        ClinicSpecialty clinicSpecialty = clinicSpecialtyRepository.findByClinicIdAndSpecialtyIdAndDoctorInforId(dto.getClinicId(), dto.getSpecialtyId(), dto.getDoctorId())
                .orElse(null);
        entity.setClinicSpecialties(clinicSpecialty);
    }
    @BeanMapping(qualifiedByName = "updateDoctorInfor", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "contentHTML", target = "contentHTML")
    @Mapping(source = "contentMarkdown", target = "contentMarkdown")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "selectedPrice", target = "price")
    @Mapping(source = "selectedPayment", target = "payment")
    @Mapping(source = "selectedProvince", target = "province")
    public abstract void updateDoctorInfor(UpdateDoctorInforRequest dto, @MappingTarget DoctorInfor entity);


    @Named("bulkCreateSchedule")
    @BeforeMapping
    protected void buikcreateScheduleBefore(BulkCreateSchedule dto, @MappingTarget Schedule entity) {
        entity.setCurrentnumber(dto.getArrSchedule().getCurrentNumber());
        entity.setMaxNumber(dto.getArrSchedule().getMaxNumber());
        entity.setDate(dto.getArrSchedule().getDate().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
        entity.setTimeType(dto.getArrSchedule().getTimeType());
    }
    @BeanMapping(qualifiedByName = "bulkCreateSchedule", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "doctorId", target = "doctorInfor.id")
    @Mapping(source = "formatedDate", target = "date")
    public abstract Schedule bulkCreateSchedule( BulkCreateSchedule request);

    @Mapping(source = "user.role.id", target = "roleId")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    public abstract DoctorSearchDto doctorSearchDto(DoctorInfor doctorInfor);
    public abstract List<DoctorSearchDto> doctorSearchDtoList(List<DoctorInfor> doctorInfors);
}
