package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Models.Theater;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    @Query(value = "select * from Theaters where theater_id=:theater_id", nativeQuery = true)
    Optional<Theater> getTheater(@Param("theater_id") Integer theater_id);



}
