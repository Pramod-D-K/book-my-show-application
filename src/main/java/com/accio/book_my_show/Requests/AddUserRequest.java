package com.accio.book_my_show.Requests;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class AddUserRequest {


    private String name;

    private String mobileNo;

    @Email
    private String emailId;

}
