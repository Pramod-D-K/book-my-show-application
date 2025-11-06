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
        List<Show> allShows = showRepository.findAll();
        List<Show> filteredShows = allShows.stream()
                .filter(show -> movieName == null || show.getMovie().getName().equalsIgnoreCase(movieName))
                .filter(show -> city == null || show.getTheater().getCity().equalsIgnoreCase(city))
                .filter(show -> showDate == null || show.getShowDate().equals(showDate))
                .filter(show -> showTime == null || show.getShowTime().equals(showTime))
                .filter(show -> language == null || show.getMovie().getLanguage() == language)
                .filter(show -> genre == null || show.getMovie().getGenre() == genre)
                .toList();
//        if(movieName!=null&& city!=null && showDate!=null&& showTime!=null && language!=null&&genre!=null){
//            List<Show>showList=showRepository.findShowByMovie_NameIgnoreCaseAndTheater_CityIgnoreCaseAndMovie_languageAndMovie_GenreAndShowDateAndShowTime(
//                    movieName,city,language,genre,showDate,showTime);
//            if(showList.isEmpty()){
//                throw new ResourceNotFoundException("Show not found");
//            }
//            for (Show show:showList){
//                GetShowResponse showResponse= GetShowResponse.builder()
//                        .showTime(show.getShowTime())
//                        .showDate(show.getShowDate())
//                        .city(show.getTheater().getCity())
//                        .theaterName(show.getTheater().getName())
//                        .movieName(show.getMovie().getName())
//                        .build();
//                ans.add(showResponse);
//            }
//            return ans;
//        }
//
//        List<Show> allShowList2 = showRepository.findAll();
//        List<Show> allShowList1= new ArrayList<>(allShowList2);
//        if(movieName!=null){
//            List<Show>showListByMovie=showRepository.getShowListByMovieName(movieName);
//            allShowList2.removeAll(showListByMovie);
//        }
//        if(city!=null){
//            List<Show>showListByCity=showRepository.findAllByTheater_CityIgnoreCase(city);
//            allShowList2.removeAll(showListByCity);
//        }
//        if(language!=null){
//            List<Show> showListByLanguage =showRepository.findAllByMovie_Language(language);
//            allShowList2.removeAll(showListByLanguage);
//        }
//        if(genre!=null){
//            List<Show> showListByGenre =showRepository.findAllByMovie_Genre(genre);
//            allShowList2.removeAll(showListByGenre);
//        }
//        if(showDate!=null){
//            List<Show> showListByDate =showRepository.findAllByShowDate(showDate);
//            allShowList2.removeAll(showListByDate);
//        }
//        if(showTime!=null){
//            List<Show> showListByTime =showRepository.findAllByShowTime(showTime);
//            allShowList2.removeAll(showListByTime);
//        }
//        allShowList1.removeAll(allShowList2);

        if(allShows.isEmpty()){
            throw new ResourceNotFoundException("Show not found");
        }
        for (Show show:allShows){
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

    public String deleteShow(DeleteShowRequest deleteShowRequest){
        Integer showId= deleteShowRequest.getShowId();
        Optional<Show> optionalShow= showRepository.findById(showId);
        Show show= optionalShow.orElseThrow(()-> new ResourceNotFoundException("Show not found"));
        showRepository.deleteById(showId);
        return  "Show has been deleted";
    }
}
