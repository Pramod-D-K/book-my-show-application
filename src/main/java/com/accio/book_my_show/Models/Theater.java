package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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

    @Size(min = 1,message = "Name should be null")
    private String name;

    @Column(length = 500)
    @Size(min = 1,message = "Name should be null")
    private String address;

    @Min(value = 1,message = "NoOfScreen should be greater than or equal to 1")
    @Max(value = 10,message = "NoOfScreen should be smaller than or equal to 10")
    private Integer noOfScreens;

    //not create any tables because
    @OneToMany(mappedBy = "theater",cascade=CascadeType.ALL)
    private List<TheaterSeat>theaterSeatList=new ArrayList<>();
}
