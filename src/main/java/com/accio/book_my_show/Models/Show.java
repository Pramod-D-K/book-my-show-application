package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Shows",uniqueConstraints = @UniqueConstraint(
        columnNames = {"showDate", "showTime", "movie_movie_id", "theater_theater_id"}
))
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

    private LocalTime showTime;

    @JoinColumn
    @ManyToOne
    private Movie movie;

    @JoinColumn
    @ManyToOne
    private Theater theater;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL)
    List<ShowSeat>showSeatList=new ArrayList<>();
}
