package com.uit.bookingcare.service.impl;

import com.uit.bookingcare.dto.DataMailDTO;
import com.uit.bookingcare.service.ClientService;
import com.uit.bookingcare.service.MailService;
import com.uit.bookingcare.service.sdi.ClientSdi;
import com.uit.bookingcare.utils.Const;
import com.uit.bookingcare.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;

    @Override
    public Boolean create(ClientSdi sdi) {
        try {

            DataMailDTO dataMail = new DataMailDTO();

            dataMail.setTo(sdi.getEmail());
            dataMail.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

            Map<String, Object> props = new HashMap<>();
            props.put("name", sdi.getName());
            props.put("doctorName", sdi.getDoctorName());
            props.put("time",sdi.getTime());
            props.put("address",sdi.getAddress());
            props.put("token", sdi.getToken());
            dataMail.setProps(props);

            mailService.sendHtmlMail(dataMail, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
            return true;
        } catch (MessagingException exp){
            exp.printStackTrace();
        }
        return false;
    }

}
