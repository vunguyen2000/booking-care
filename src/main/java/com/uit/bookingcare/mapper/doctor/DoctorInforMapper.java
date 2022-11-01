package com.uit.bookingcare.mapper.doctor;

import com.uit.bookingcare.constant.enums.EPayment;
import com.uit.bookingcare.constant.enums.EPrice;
import com.uit.bookingcare.constant.enums.EProvince;
import com.uit.bookingcare.domain.doctor.DoctorInfor;
import com.uit.bookingcare.dto.doctor.*;
import com.uit.bookingcare.mapper.MapperBase;
import com.uit.bookingcare.repository.clinic.ClinicRepository;
import com.uit.bookingcare.repository.doctorinfor.DoctorInforRepository;
import com.uit.bookingcare.repository.specialty.SpecialtyRepository;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.request.doctor.UpdateDoctorInforRequest;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class DoctorInforMapper implements MapperBase {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorInforRepository doctorInforRepository;

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.address", target = "address")
    @Mapping(source = "user.phonenumber", target = "phonenumber")
    @Mapping(source = "user.email", target = "email")
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
    }
    @BeanMapping(qualifiedByName = "toExtraDoctorInforDto" ,
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "specialty.id", target = "specialtyId")
    @Mapping(source = "clinic.id", target = "clinicId")
    @Mapping(source = "price", target = "priceId")
    @Mapping(source = "province", target = "provinceId")
    @Mapping(source = "payment", target = "paymentId")
    @Mapping(source = "clinic.address", target = "addressClinic")
    @Mapping(source = "clinic.name", target = "nameClinic")
    @Mapping(source = "count", target = "count")
    @Mapping(source = "note", target = "note")
    public abstract DoctorExtraDto toExtraDoctorInforDto(DoctorInfor doctorInfor);

    @Named("updateDoctorInfor")
    @BeforeMapping
    protected void updateDoctorInforBefore(UpdateDoctorInforRequest dto, @MappingTarget DoctorInfor entity) {
        if (dto.getClinicId() != null){
            clinicRepository.findById(dto.getClinicId()).ifPresent(entity::setClinic);
        }

        if (dto.getSpecialtyId() != null){
            specialtyRepository.findById(dto.getSpecialtyId()).ifPresent(entity::setSpecialty);
        }
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

}
