package com.accio.book_my_show.Responses;

import com.accio.book_my_show.Models.Theater;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class GetBookedTicketResponse {
    private String userName;

    private String movieName;

    private String theaterName;

    private String theaterAddress;

    private Integer totalAmount;

    private LocalDate showDate;

    private LocalTime showTime;

    private List<String> bookedSeats;

    private List<String> notBookedSeats;

    private String status;
}
