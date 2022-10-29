package com.uit.bookingcare.domain.patient;

import com.uit.bookingcare.domain.history.History;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<History> histories = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<Schedule> schedules = new ArrayList<>();

}
