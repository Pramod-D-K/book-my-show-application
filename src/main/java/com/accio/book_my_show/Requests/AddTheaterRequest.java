package com.accio.book_my_show.Requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTheaterRequest {

    private String name;

    private String address;

    private Integer noOfScreens;

}
