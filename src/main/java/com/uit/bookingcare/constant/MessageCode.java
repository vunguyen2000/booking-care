package com.uit.bookingcare.constant;

public interface MessageCode {
    interface User {
        String WRONG = "message.user.Wrong";
        String WRONG_PASS = "message.user.WrongPass";
        String INVALID = "message.user.Invalid";
        String NOT_FOUND = "message.user.NotFound";
        String EXIST = "message.user.Exist";
    }
    interface Token {
        String INVALID_TOKEN = "message.token.InvalidToken";
    }
    interface Clinic {
        String EXIST = "message.clinic.exists";
        String NOT_FOUND = "message.clinic.NotFound";
        String INVALID = "message.clinic.Invalid";
        String NOT_DELETED = "message.clinic.NotDeleted";
    }
    interface Specialty {
        String EXIST = "message.specialty.exists";
        String NOT_FOUND = "message.specialty.NotFound";
        String INVALID = "message.specialty.Invalid";
        String NOT_DELETED = "message.specialty.NotDeleted";
    }
    interface DoctorInfor {
        String EXIST = "message.doctorInfor.exists";
        String NOT_FOUND = "message.doctorInfor.NotFound";
        String INVALID = "message.doctorInfor.Invalid";
        String NOT_DELETED = "message.doctorInfor.notDeleted";
    }

    interface Booking {
        String NOT_FOUND = "message.booking.NotFound";
        String IS_COMPLETED = "message.booking.IsCompleted";
    }
}
