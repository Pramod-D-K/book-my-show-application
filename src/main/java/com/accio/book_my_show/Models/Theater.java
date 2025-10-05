package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Theaters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Integer theaterId;

    private String name;

    @Column(length = 500)
    private String address;

    private Integer noOfScreens;

    //not create any tables because
    @OneToMany(mappedBy = "theater",cascade=CascadeType.ALL)
    private List<TheaterSeat>theaterSeatList=new ArrayList<>();
}
