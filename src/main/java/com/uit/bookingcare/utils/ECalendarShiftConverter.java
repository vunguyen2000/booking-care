package com.uit.bookingcare.utils;

import com.uit.bookingcare.constant.enums.ETimeType;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class ECalendarShiftConverter implements AttributeConverter<List<ETimeType>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<ETimeType> stringList) {
        if (stringList == null){
            return "";
        }
        ETimeType[] array = new ETimeType[stringList.size()];
        stringList.toArray(array);
        return  Stream.of(array)
                .map(Enum::name)
                .collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<ETimeType> convertToEntityAttribute(String string) {
        if (StringUtils.isEmpty(string)){
            return Collections.emptyList();
        }

        return string != null ? Arrays.stream(string.split(SPLIT_CHAR))
                .map(ETimeType::valueOf)
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}
