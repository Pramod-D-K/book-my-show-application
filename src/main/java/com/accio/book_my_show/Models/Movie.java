package com.accio.book_my_show.Models;


import com.accio.book_my_show.Enums.MovieGenre;
import com.accio.book_my_show.Enums.MovieLanguage;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Size(min = 1,message = "Name must not be empty")
    private String name;

    @Min(value = 1,message = "Duration must not be zero")
    private double duration;

    @Column(precision = 3, scale = 1)
    @DecimalMin(value = "0.1",message = "Rating should be greater than zero")
    @DecimalMax(value = "10.0", message = "Rating must smaller than or equal to 10")
    private BigDecimal rating;

    private LocalDate releaseDate;

    @Enumerated(value = EnumType.STRING)
    private MovieGenre genre;

    @Enumerated(value = EnumType.STRING)
    private MovieLanguage language;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> showList=new ArrayList<>();
}
