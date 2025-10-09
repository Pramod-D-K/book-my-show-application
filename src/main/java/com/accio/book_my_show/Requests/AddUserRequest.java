package com.accio.book_my_show.Requests;

import lombok.Data;

@Data
public class AddUserRequest {
    private String userName;

    private String mobileNo;

    private String emailId;

    private double age;
}
