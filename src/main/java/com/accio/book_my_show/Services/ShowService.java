package com.accio.book_my_show.Services;

import com.accio.book_my_show.Enums.MovieGenre;
import com.accio.book_my_show.Enums.MovieLanguage;
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
import com.accio.book_my_show.Responses.GetShowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
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

    public List<GetShowResponse> getShowByMovieName(String movieName){

        List<GetShowResponse> ans= new ArrayList<>();
        List<Movie> movieList= movieRepository.findAll();
        if(movieList.isEmpty()){
            throw new EmptyResultDataAccessException("Movie database is Empty",1);
        }
        Optional<Movie> optionalMovie=movieRepository.getMovie(movieName);
        Movie movie1= optionalMovie.orElseThrow(()->new ResourceNotFoundException("movie not found"));

        List<Show> showList= showRepository.getShowListByMovieName(movieName);
        if(showList.isEmpty()){
            throw new ResourceNotFoundException("Shows not found");
        }
        for (Show show:showList){
            GetShowResponse showResponse= GetShowResponse.builder()
                    .showTime(show.getShowTime())
                    .showDate(show.getShowDate())
                    .city(show.getTheater().getCity())
                    .theaterName(show.getTheater().getName())
                    .movieName(show.getMovie().getName())
                    .build();
            ans.add(showResponse);
        }
        return ans;
    }

    //by theaterId
    public List<GetShowResponse> getShowByTheater(Integer theaterId){
        List<GetShowResponse> ans= new ArrayList<>();
        Optional<Theater>optionalTheater=theaterRepository.findById(theaterId);
        Theater theater1= optionalTheater.orElseThrow(()->new ResourceNotFoundException("Theater not found"));

        List<Show> showList= showRepository.findShowByTheater_TheaterId(theaterId);
        if(showList.isEmpty()){
            throw new ResourceNotFoundException("Shows not found");
        }
        for (Show show:showList){
            GetShowResponse showResponse= GetShowResponse.builder()
                    .showTime(show.getShowTime())
                    .showDate(show.getShowDate())
                    .city(show.getTheater().getCity())
                    .theaterName(show.getTheater().getName())
                    .movieName(show.getMovie().getName())
                    .build();
            ans.add(showResponse);
        }
        return ans;
    }

    public List<GetShowResponse> getShowByFilter(String movieName, String city,
                                                 LocalDate showDate, LocalTime showTime,
                                                 MovieLanguage language, MovieGenre genre){
        List<GetShowResponse> ans=new ArrayList<>();
        if(movieName!=null&& city!=null && showDate!=null&& showTime!=null && language!=null&&genre!=null){
            List<Show>showList=showRepository.findShowByMovie_NameIgnoreCaseAndTheater_CityIgnoreCaseAndMovie_languageAndMovie_GenreAndShowDateAndShowTime(
                    movieName,city,language,genre,showDate,showTime);
            if(showList.isEmpty()){
                throw new ResourceNotFoundException("Show not found");
            }
            for (Show show:showList){
                GetShowResponse showResponse= GetShowResponse.builder()
                        .showTime(show.getShowTime())
                        .showDate(show.getShowDate())
                        .city(show.getTheater().getCity())
                        .theaterName(show.getTheater().getName())
                        .movieName(show.getMovie().getName())
                        .build();
                ans.add(showResponse);
            }
//            return ans;
        }

//        if(movieName!=null){
//            List<Movie> movieList= movieRepository.findAll();
//            if(movieList.isEmpty()){
//                throw new EmptyResultDataAccessException("Movie database is Empty",1);
//            }
//            Optional<Movie> optionalMovie=movieRepository.getMovie(movieName);
//            Movie movie1= optionalMovie.orElseThrow(()->new ResourceNotFoundException("movie not found"));
//
//            List<Show> showList= showRepository.getShowListByMovieName(movieName);
//            if(showList.isEmpty()){
//                throw new ResourceNotFoundException("Shows not found");
//            }
//
//        }

        return ans;
    }

    public String deleteShow(DeleteShowRequest deleteShowRequest){
        Integer showId= deleteShowRequest.getShowId();
        Optional<Show> optionalShow= showRepository.findById(showId);
        Show show= optionalShow.orElseThrow(()-> new ResourceNotFoundException("Show not found"));
        showRepository.deleteById(showId);
        return  "Show has been deleted";
    }
}
