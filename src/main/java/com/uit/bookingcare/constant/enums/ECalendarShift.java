package com.uit.bookingcare.constant.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum ECalendarShift {
    SHIFT_1("Ca 1", "8:00", "9:00", 7, 30, 8, 15),
    SHIFT_2("Ca 2", "9:00", "10:00", 8, 15, 9, 0),
    SHIFT_3("Ca 3", "10:00", "11:00", 9, 0, 9, 45),
    SHIFT_4("Ca 4", "11:00", "12:00", 10, 0, 10, 45),
    SHIFT_5("Ca 5", "13:00", "14:00", 10, 45, 11, 30),
    SHIFT_6("Ca 6", "14:00", "15:00", 13, 0, 13, 45),
    SHIFT_7("Ca 7", "15:00", "16:00", 13, 45, 14, 30),
    SHIFT_8("Ca 8", "16:00", "17:00", 14, 30, 15, 15);


    @Getter
    private final String value;

    @Getter
    private final String fromTime;

    @Getter
    private final String toTime;

    @Getter
    private final Integer hourFrom;

    @Getter
    private final Integer minuteFrom;

    @Getter
    private final Integer hourTo;

    @Getter
    private final Integer minuteTo;


    ECalendarShift(String value, String fromTime, String toTime, Integer hourFrom, Integer minuteFrom,
                   Integer hourTo, Integer minuteTo) {
        this.value = value;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.hourFrom = hourFrom;
        this.minuteFrom = minuteFrom;
        this.hourTo = hourTo;
        this.minuteTo = minuteTo;
    }

    public String getValueString() {
        return value + "(" + fromTime + " - " + toTime + ")";
    }

    public static Map<String, String> getMapEnum() {
        Map<String, String> rs = new HashMap<>();
        Stream.of(ECalendarShift.values()).collect(Collectors.toList()).forEach(eCalendarShift -> {
            rs.put(eCalendarShift.name(), eCalendarShift.getValueString());
        });
        return rs;
    }
}
