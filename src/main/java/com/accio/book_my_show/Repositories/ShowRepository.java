package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Repository

public interface ShowRepository extends JpaRepository<Show,Integer> {

    Optional<Show> findShowByShowDateAndShowTimeAndMovie_MovieIdAndTheater_TheaterId(LocalDate showDate,
                                                                   LocalTime showTime,
                                                                   Integer movieId,
                                                                   Integer theaterId);

    @Query(value = "select * from Shows where show_date=?1 and show_time=?2 and movie_movie_id=?3 and theater_theater_id=?4",nativeQuery = true)
    Optional<Show> getShow(LocalDate show_date, LocalTime show_time,Integer movie_id,Integer theater_id);

    ///getting list of seats by the

}
