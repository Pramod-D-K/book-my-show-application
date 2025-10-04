package com.accio.book_my_show.Services;

import com.accio.book_my_show.Controllers.MovieController;
import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Repositories.MovieRepository;
import com.accio.book_my_show.Requests.AddMovieRequest;
import com.accio.book_my_show.Requests.UpdateRatingAndDuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest) throws Exception{

        if(addMovieRequest==null){
            throw new Exception("Given movie is null");
        }
        Movie movie=Movie.builder().name(addMovieRequest.getName())
                .language(addMovieRequest.getLanguage())
                .genre(addMovieRequest.getGenre())
                .rating(addMovieRequest.getRating())
                .releaseDate(addMovieRequest.getReleaseDate())
                .duration(addMovieRequest.getDuration()).build();
        movieRepository.save(movie);
        return "Movie  "+movie.getName()+"  has been added ";
    }

    public String updateMovieRatingAndDuration(
            UpdateRatingAndDuration updateRatingAndDuration)throws Exception{
        Optional<Movie> optionalMovie= movieRepository.findById(updateRatingAndDuration.getMovieId());
        Movie movie= optionalMovie.orElseThrow(()-> new Exception("Movie not present"));
        movie.setDuration(updateRatingAndDuration.getDuration());
        movie.setRating(updateRatingAndDuration.getRating());
        movie=movieRepository.save(movie);
        return "Movie "+movie.getName()+" has been updated";
    }

    public String clearMovies() throws Exception{
        movieRepository.deleteAll();
        return "All Movies deleted";
    }
}
