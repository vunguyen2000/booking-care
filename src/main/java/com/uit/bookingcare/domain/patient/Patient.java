package com.uit.bookingcare.domain.patient;

import com.uit.bookingcare.domain.booking.Booking;
import com.uit.bookingcare.domain.history.Histories;
import com.uit.bookingcare.domain.schedule.Schedule;
import com.uit.bookingcare.domain.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

}
