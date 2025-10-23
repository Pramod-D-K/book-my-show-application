package com.accio.book_my_show.Requests;

import com.accio.book_my_show.Enums.SeatStatus;
import lombok.Data;

@Data
public class UpdateSeatStatus {

    private Integer seatId;
    private SeatStatus seatStatus;
}
