package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer theaterId;

    private String name;

    @Column(length = 500)
    private String address;

    private Integer noOfScreens;

}
