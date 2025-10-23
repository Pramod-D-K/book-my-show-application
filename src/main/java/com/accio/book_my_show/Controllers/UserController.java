package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.AddUserRequest;
import com.accio.book_my_show.Services.MailService;
import com.accio.book_my_show.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@Valid @RequestBody  AddUserRequest addUserRequest){
            String ans= userService.addUser(addUserRequest);
            return ResponseEntity.ok().body(ans);



    }
}
