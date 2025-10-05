package com.accio.book_my_show.Models;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "TheaterSeats")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterSeatId;

    @Size(min = 1,message = "Name should be null")
    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @Enumerated(value = EnumType.STRING)
    private SeatStatus seatStatus;

    @JoinColumn
    @ManyToOne
    private Theater theater;
}
