package com.accio.book_my_show.Requests;

import lombok.Data;
import lombok.NonNull;

@Data
public class AddShoeSeatRequest {

    @NonNull
    private Integer showId;

    private Integer priceOfClassicSeat;

    private Integer priceOfPremiumSeat;
}
