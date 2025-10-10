package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import com.accio.book_my_show.Generators.*;


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
    @GeneratedValue(generator = "ticketGenerator")
    @GenericGenerator(name = "ticketGenerator", strategy = "com.accio.book_my_show.Generators.CustomTicketGenerator")
    private String ticketId;

    @FutureOrPresent(message = "date should not be past")
    private LocalDate showDate;

    private LocalTime showTime;

    private String movieName;

    private String theaterName;

    private String theaterAddress;

    private Integer totalAmount;

    //private List<String> bookedSeats;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn
    @ManyToOne
    private Show show;
}
