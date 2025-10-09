package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByMobileNo(String mobileNo);

    @Query(value = "select * from Users where mobile_no=:mobile_no",nativeQuery = true)
    Optional<User>getUser(@Param("mobile_no") String mobile_no);
}
