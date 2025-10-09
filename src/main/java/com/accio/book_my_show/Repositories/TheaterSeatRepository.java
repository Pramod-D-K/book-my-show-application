package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Models.TheaterSeat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeat,Integer> {

    @Modifying
    @Transactional
    @Query(value = "update theater_seats set seat_status=?2 where theater_seat_id=?1",nativeQuery = true)
    int updateTheaterSeatStatus(Integer theater_seat_id, SeatStatus seat_status);
}
