package com.accio.book_my_show.Models;


import com.accio.book_my_show.Enums.MovieGenre;
import com.accio.book_my_show.Enums.MovieLanguage;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false)
    private Integer movieId;

    @Column(unique = true)
    private String name;

    private double duration;

    @Column(precision = 3, scale = 1)
    private BigDecimal rating;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private MovieGenre genre;

    @Enumerated(value = EnumType.STRING)
    private MovieLanguage language;
}
