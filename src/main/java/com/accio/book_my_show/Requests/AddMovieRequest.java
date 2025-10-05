package com.accio.book_my_show.Requests;

import com.accio.book_my_show.Enums.MovieGenre;
import com.accio.book_my_show.Enums.MovieLanguage;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

public class AddMovieRequest {

    private String name;

    private double duration;

    private BigDecimal rating;

    private LocalDate releaseDate;

    private MovieGenre genre;

    private MovieLanguage language;
}
