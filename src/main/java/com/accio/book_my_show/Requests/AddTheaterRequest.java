package com.accio.book_my_show.Requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterRequest {

    @Size(min = 1,message = "Name should be null")
    private String name;

    @Size(min = 1,message = "Address should be null")
    private String address;

    @Min(value = 1,message = "NoOfScreen should be greater than or equal to 1")
    @Max(value = 10,message = "NoOfScreen should be smaller than or equal to 10")
    private Integer noOfScreens;

}
