package com.accio.book_my_show.Services;

import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Repositories.MovieRepository;
import com.accio.book_my_show.Requests.AddMovieRequest;
import com.accio.book_my_show.Requests.DeleteMovieRequest;
import com.accio.book_my_show.Requests.UpdateRatingAndDuration;
import com.accio.book_my_show.Responses.GetMovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(AddMovieRequest addMovieRequest) throws Exception{

        if(addMovieRequest==null){
            throw new Exception("Given movie is null");
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
            UpdateRatingAndDuration updateRatingAndDuration)throws Exception{
        Optional<Movie> optionalMovie= movieRepository.findById(updateRatingAndDuration.getMovieId());
        Movie movie= optionalMovie.orElseThrow(()-> new Exception("Movie not present"));

        int updateDur=movieRepository.updateDuration(updateRatingAndDuration.getDuration(),
                updateRatingAndDuration.getMovieId());
        int updateRat =movieRepository.updateRating(updateRatingAndDuration.getRating(),
                updateRatingAndDuration.getMovieId());
//        movie.setDuration(updateRatingAndDuration.getDuration());
//        movie.setRating(updateRatingAndDuration.getRating());
        movie=movieRepository.save(movie);
        return "Movie "+movie.getName()+" has been updated";
    }
    public List<GetMovieResponse> getMovieResponseList()throws Exception{
        List<Movie> movies=movieRepository.findAll();
        if(movies.isEmpty()){
            throw new Exception("Movie DataBase is Empty");
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

    public String deleteMovie(DeleteMovieRequest movieRequest)throws Exception{
        Integer movieId= movieRequest.getMovieId();
        Optional<Movie> optionalMovie=movieRepository.findById(movieId);
        Movie movie=optionalMovie.orElseThrow(()-> new Exception("Movie not found by this Id"));
        movieRepository.deleteById(movieId);
        return "Movie has been deleted";
    }

    public String clearMovies() throws Exception{
        movieRepository.deleteAll();
        return "All Movies deleted";
    }
}
