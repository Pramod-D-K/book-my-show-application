package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Models.ShowSeat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Integer> {


}
