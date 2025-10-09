package com.accio.book_my_show.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="user_id")
    @SequenceGenerator(name = "user_id",sequenceName = "AllUserId",allocationSize = 1)
    private Integer userId;

    private String name;

    @Column(unique = true)
    private String emailId;

    @Column(unique = true)
    private String mobileNo;
}
