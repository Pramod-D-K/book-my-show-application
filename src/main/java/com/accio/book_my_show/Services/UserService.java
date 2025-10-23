package com.accio.book_my_show.Services;

import com.accio.book_my_show.Models.User;
import com.accio.book_my_show.Repositories.UserRepository;
import com.accio.book_my_show.Requests.AddUserRequest;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    public String addUser(AddUserRequest addUserRequest) {


        User user=User.builder()
                .userName(addUserRequest.getName())
                .emailId(addUserRequest.getEmailId())
                .mobileNo(addUserRequest.getMobileNo())
                .build();
        user=userRepository.save(user);
        mailService.sendWelcomeMail(user);
        return "User saved to Db with user Id "+user.getUserId();
    }

}
