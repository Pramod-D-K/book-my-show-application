package com.accio.book_my_show.Requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class UpdateRatingAndDuration {
    private Integer movieId;

    private double duration;

    private BigDecimal rating;
}
