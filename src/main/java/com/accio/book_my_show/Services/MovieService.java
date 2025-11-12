package com.accio.book_my_show.Services;

import com.accio.book_my_show.Exceptions.ResourceNotFoundException;
import com.accio.book_my_show.Exceptions.GlobalExceptionHandler.*;

import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Models.Ticket;
import com.accio.book_my_show.Repositories.MovieRepository;
import com.accio.book_my_show.Repositories.TicketRepository;
import com.accio.book_my_show.Requests.AddMovieRequest;
import com.accio.book_my_show.Requests.DeleteMovieRequest;
import com.accio.book_my_show.Requests.UpdateRatingAndDuration;
import com.accio.book_my_show.Responses.GetMovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TicketRepository ticketRepository;

    public String addMovie(AddMovieRequest addMovieRequest){

        if(addMovieRequest==null){
            throw new RuntimeException ("Given movie is null");
        }
        Movie movie=Movie.builder()
                .name(addMovieRequest.getName())
                .language(addMovieRequest.getLanguage())
                .genre(addMovieRequest.getGenre())
                .rating(addMovieRequest.getRating())
                .releaseDate(addMovieRequest.getReleaseDate())
                .duration(addMovieRequest.getDuration()).build();
        movieRepository.save(movie);
        return "Movie  "+movie.getName()+"  has been added ";
    }

    public String updateMovieRatingAndDuration(
            UpdateRatingAndDuration updateRatingAndDuration){
        Optional<Movie> optionalMovie= movieRepository.findById(updateRatingAndDuration.getMovieId());
        Movie movie= optionalMovie.orElseThrow(()-> new ResourceNotFoundException("Movie not present"));

        int updateDur=movieRepository.updateDuration(updateRatingAndDuration.getDuration(),
                updateRatingAndDuration.getMovieId());
        int updateRat =movieRepository.updateRating(updateRatingAndDuration.getRating(),
                updateRatingAndDuration.getMovieId());
//        movie.setDuration(updateRatingAndDuration.getDuration());
//        movie.setRating(updateRatingAndDuration.getRating());
        movie=movieRepository.save(movie);
        return "Movie "+movie.getName()+" has been updated";
    }
    public List<GetMovieResponse> getMovieResponseList(){
        List<Movie> movies=movieRepository.findAll();
        if(movies.isEmpty()){
            throw new RuntimeException("Movie DataBase is Empty");
        }
        List<GetMovieResponse>movieResponseList=new ArrayList<>();

        for(Movie movie: movies){
            GetMovieResponse getMovieResponse= GetMovieResponse.builder()
                    .name(movie.getName())
                    .rating(movie.getRating())
                    .duration(movie.getDuration())
                    .language(movie.getLanguage())
                    .build();
            movieResponseList.add(getMovieResponse);
        }
        return movieResponseList;
    }

    public long getTotalAmountByMovie(Integer movieId){
        long total = ticketRepository.getTotalAmount(movieId);
        return total;
    }

    public long getTotalRevenueByPeriod(Integer movieId,LocalDate startDate, LocalDate endDate){
        List<Ticket> ticketByMovie= ticketRepository.grtTicketByMovie(movieId);
        if(ticketByMovie.isEmpty()){
            throw  new ResourceNotFoundException("Movie not found by this movieId");
        }
        long total = 0;

        for (Ticket ticket : ticketByMovie) {
            LocalDate showDate = ticket.getShowDate();

            boolean afterStart = (startDate == null) || !showDate.isBefore(startDate);
            boolean beforeEnd = (endDate == null) || !showDate.isAfter(endDate);

            if (afterStart && beforeEnd) {
                total += ticket.getTotalAmount();
            }
        }

        return total;
    }

    public String deleteMovie(Integer movieId){
        Optional<Movie> optionalMovie=movieRepository.findById(movieId);
        Movie movie=optionalMovie.orElseThrow(()-> new ResourceNotFoundException("Movie not found by this Id"));
        movieRepository.deleteById(movieId);
        return "Movie has been deleted";
    }

    public String clearMovies(){
        movieRepository.deleteAll();
        return "All Movies deleted";
    }
}
