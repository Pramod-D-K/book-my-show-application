package com.accio.book_my_show.Models;

import com.accio.book_my_show.Enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ShowSeats",uniqueConstraints = @UniqueConstraint(
        columnNames = {"seatNo","show_show_id"}
))
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String seatNo;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    private Integer price;

    private Boolean isAvailable;

    @JoinColumn
    @ManyToOne
    private Show show;
}
