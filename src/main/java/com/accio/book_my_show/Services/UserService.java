package com.accio.book_my_show.Services;

import com.accio.book_my_show.Models.User;
import com.accio.book_my_show.Repositories.UserRepository;
import com.accio.book_my_show.Requests.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String addUser(AddUserRequest addUserRequest) throws Exception{

        User user=User.builder()
                .userName(addUserRequest.getName())
                .emailId(addUserRequest.getEmailId())
                .mobileNo(addUserRequest.getMobileNo())
                .build();
        user=userRepository.save(user);
        return "User saved to Db with user Id "+user.getUserId();
    }
}
