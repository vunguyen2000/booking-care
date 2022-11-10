package com.uit.bookingcare.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.dto.response.FileCaption;
import com.uit.bookingcare.dto.user.UserDto;
import org.apache.commons.text.StringEscapeUtils;
import org.mapstruct.Named;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public interface MapperBase {

//    @Named("getAudit")
//    default UserDto getAudit(User user) {
//        if (user == null) return null;
//        Gson gson = new Gson();
//        return new UserDto(user.getId(),user.getEmail(),gson.fromJson(StringEscapeUtils.unescapeHtml4(user.getImage()), FileCaption.class));
//    }

//    @Named("getUserInfo")
//    default UserDto getUserInfo(User user) {
//        if (user == null) return null;
//        Gson gson = new Gson();
//        return new UserDto(user.getId(), user.getEmail(), gson.fromJson(StringEscapeUtils.unescapeHtml4(user.getImage()), FileCaption.class));
//    }

    @Named("getFile")
    default FileCaption getPhoto(String file) {
        if (file == null) return null;
        Gson gson = new Gson();
        return gson.fromJson(StringEscapeUtils.unescapeHtml4(file), FileCaption.class);
    }

    @Named("getFiles")
    default List<FileCaption> getFiles(String file) {
        if (file == null) return null;
        Gson gson = new Gson();
        return gson.fromJson(StringEscapeUtils.unescapeHtml4(file), new TypeToken<List<FileCaption>>() {
        }.getType());
    }

    @Named("setFile")
    default String setFile(FileCaption fileCaption) {
        if (fileCaption == null) return null;
        Gson gson = new Gson();
        return gson.toJson(fileCaption);
    }

    @Named("setFiles")
    default String setFiles(List<FileCaption> captions) {
        if (captions == null) return null;
        Gson gson = new Gson();
        return gson.toJson(captions);
    }

    @Nullable
    default Long localDateToLong(@Nullable LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Nullable
    default LocalDate longToLocalDate(@Nullable Long milli) {
        if (milli == null) {
            return null;
        }

        return Instant.ofEpochMilli(milli).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
