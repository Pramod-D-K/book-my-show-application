package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.ShowSeat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {

    ///get parent from child
    Optional<Show> findShowByShow_ShowDateAndShow_ShowTimeAndShow_Movie_MovieIdAndShow_Theater_TheaterId(
            LocalDate showDate,
            LocalTime showTime,
            Integer movieId,
            Integer theaterId);

    ///By show
    ///native query not possible for this
    List<ShowSeat> findAllByShow(Show show);

    ///by show id
    List<ShowSeat> findAllByShow_ShowId(Integer showId);

    @Query(value = "select * from Show_seats where show_show_id=?1",nativeQuery = true)
    List<ShowSeat> getShowSeats(Integer showId);
    ///

}
