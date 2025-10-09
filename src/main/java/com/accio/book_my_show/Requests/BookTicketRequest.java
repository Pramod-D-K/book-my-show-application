package com.accio.book_my_show.Requests;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest {
    private String movieName;

    private LocalDate showDate;

    private LocalTime showTime;

    private Integer theaterId;

    private List<String> requestSeats;

    private String mobileNo;

}
