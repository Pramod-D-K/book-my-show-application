package com.accio.book_my_show.Services;

import com.accio.book_my_show.Exceptions.ResourceNotFoundException;
import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Repositories.MovieRepository;
import com.accio.book_my_show.Repositories.ShowRepository;
import com.accio.book_my_show.Repositories.TheaterRepository;
import com.accio.book_my_show.Requests.AddMovieRequest;
import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Requests.DeleteShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(AddShowRequest addShowRequest){
        List<Show> showList=new ArrayList<>();
        Optional<Movie> optionalMovie=movieRepository.getMovie(addShowRequest.getMovieName());
        Movie movie1= optionalMovie.orElseThrow(()->new ResourceNotFoundException("movie not found"));

        Optional<Theater>optionalTheater=theaterRepository.getTheater(addShowRequest.getTheaterId());
        Theater theater1= optionalTheater.orElseThrow(()->new ResourceNotFoundException("Theater not found"));

        Show show= Show.builder()
                .showTime(addShowRequest.getShowTime())
                .showDate(addShowRequest.getShowDate())
                .movie(movie1)
                .theater(theater1)
                .build();
        showList.add(show);
        List<Show>movieShowList=movie1.getShowList();
        movieShowList.add(show);
        movie1.setShowList(movieShowList);
        movieRepository.save(movie1);
        //showRepository.save(show);
        return "Show has been added";
    }

    public String deleteShow(DeleteShowRequest deleteShowRequest){
        Integer showId= deleteShowRequest.getShowId();
        Optional<Show> optionalShow= showRepository.findById(showId);
        Show show= optionalShow.orElseThrow(()-> new ResourceNotFoundException("Show not found"));
        showRepository.deleteById(showId);
        return  "Show has been deleted";
    }
}
