package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {


    @Query(value = "SELECT COALESCE(SUM(total_amount), 0) FROM tickets WHERE show_movie_movie_id = ?1", nativeQuery = true)
    long getTotalAmount(Integer movieId);

    @Query(value = "select * from tickets where show_movie_movie_id = ?1", nativeQuery = true)
    List<Ticket> grtTicketByMovie(Integer movieId);

    @Query(value = "select * from tickets where show_theater_theater_id = ?1", nativeQuery = true)
    List<Ticket> ticketListByTheaterId(Integer theaterId);

}
