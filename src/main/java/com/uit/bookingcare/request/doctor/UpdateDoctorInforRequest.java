package com.uit.bookingcare.request.doctor;

import com.uit.bookingcare.constant.enums.EAction;
import com.uit.bookingcare.constant.enums.EPayment;
import com.uit.bookingcare.constant.enums.EPrice;
import com.uit.bookingcare.constant.enums.EProvince;
import lombok.Data;

@Data
public class UpdateDoctorInforRequest {
    private Long doctorId;
    private String contentHTML;
    private String description;
    private String contentMarkdown;
    private EAction action;
    private EPrice selectedPrice;
    private EPayment selectedPayment;
    private EProvince selectedProvince;
    private Long clinicId;
    private String note;
    private Long specialtyId;
}
