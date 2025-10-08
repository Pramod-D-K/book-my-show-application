package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Shows")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showId;

    @FutureOrPresent(message = "Date should not be past")
    private LocalDate showDate;

    @FutureOrPresent(message = "Time should not be past")
    private LocalTime showTime;

    @JoinColumn
    @ManyToOne
    private Movie movie;

    @JoinColumn
    @ManyToOne
    private Theater theater;
}
