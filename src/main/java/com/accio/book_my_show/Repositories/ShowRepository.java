package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Enums.MovieGenre;
import com.accio.book_my_show.Enums.MovieLanguage;
import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.ShowSeat;
import com.accio.book_my_show.Models.Theater;
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

    /// by movieName;
    List<Show> findShowByMovie_NameIgnoreCase(String movieName);

    @Query(value = "select * from Shows where lower(movie_name)=lower(?1)",nativeQuery = true)
    List<Show> getShowListByMovieName(String movie_name);

    /// by theaterId;
    List<Show> findShowByTheater_TheaterId(Integer theaterId);

    @Query(value = "select * from Shows where theater_theater_id=?1",nativeQuery = true)
    List<Show> getShowByTheaterId(Integer theater_theater_id);

    List<Show> findShowByMovie_NameIgnoreCaseAndTheater_CityIgnoreCaseAndMovie_languageAndMovie_GenreAndShowDateAndShowTime
            (String movieName, String city, MovieLanguage language, MovieGenre genre, LocalDate showDate, LocalTime showTime);

    @Query(value = "select * from Shows where lower(movie_name)=lower(?1) and lower(theater_city)=lower(?2) and movie_language=?3" +
            "and movie_genre=?4 and show_date=?5 and show_time=?6",nativeQuery = true)
    List<Show> getShowByFilter(String movieName, String city, MovieLanguage language, MovieGenre genre, LocalDate showDate, LocalTime showTime);


    List<Show> findAllByTheater_CityIgnoreCase(String city);

    List<Show> findAllByMovie_Language(MovieLanguage language);

    List<Show> findAllByMovie_Genre(MovieGenre genre);

    List<Show> findAllByShowDate(LocalDate showDate);

    List<Show> findAllByShowTime(LocalTime showTime);

    List<Show> findByMovie_NameAndShowDate(String movieName, LocalDate showDate);

}
