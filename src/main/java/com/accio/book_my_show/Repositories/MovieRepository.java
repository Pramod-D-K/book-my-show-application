package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Models.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    @Query(value = "select * from Movies where name=:name",nativeQuery = true)
    Optional<Movie> getMovie(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "update Movies set duration =?1 where movie_id=?2",nativeQuery = true)
    int updateDuration(double duration,Integer movie_id);

    @Modifying
    @Transactional
    @Query(value = "update Movies set rating=?1 where movie_id=?2",nativeQuery = true)
    int updateRating(BigDecimal rating, Integer movie_id);
}
