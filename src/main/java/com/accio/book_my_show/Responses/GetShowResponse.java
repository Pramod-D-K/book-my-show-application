package com.accio.book_my_show.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
public class GetShowResponse {
    private String movieName;

    private String theaterName;

    private String city;

    private LocalDate showDate;

    private LocalTime showTime;
}
