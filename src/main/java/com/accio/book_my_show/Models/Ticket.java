package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "Tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @FutureOrPresent(message = "date should not be past")
    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private String theaterNameAndAddress;

    private Integer totalAmount;

    private List<String> bookedSeats;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Show show;
}
