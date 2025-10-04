package com.accio.book_my_show.Requests;

import com.accio.book_my_show.Enums.SeatType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterSeatRequest {
    private Integer noOfClassicSeats;

    private Integer noOfPremiumSeats;

    private Integer noOfColumns;

    private Integer theaterId;
}
