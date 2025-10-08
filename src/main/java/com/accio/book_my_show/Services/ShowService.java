package com.accio.book_my_show.Services;

import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Repositories.MovieRepository;
import com.accio.book_my_show.Repositories.ShowRepository;
import com.accio.book_my_show.Repositories.TheaterRepository;
import com.accio.book_my_show.Requests.AddMovieRequest;
import com.accio.book_my_show.Requests.AddShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(AddShowRequest addShowRequest)throws Exception{
        Optional<Movie> optionalMovie=movieRepository.getMovie(addShowRequest.getMovieName());
        Movie movie1= optionalMovie.orElseThrow(()->new Exception("movie not found"));

        Optional<Theater>optionalTheater=theaterRepository.getTheater(addShowRequest.getTheaterId());
        Theater theater1= optionalTheater.orElseThrow(()->new Exception("Theater not found"));

        Show show= Show.builder()
                .showTime(addShowRequest.getShowTime())
                .showDate(addShowRequest.getShowDate())
                .movie(movie1)
                .theater(theater1)
                .build();

        showRepository.save(show);
        return "Show has been added";
    }
}
