package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.UpdateRatingAndDuration;
import com.accio.book_my_show.Responses.GetMovieResponse;
import com.accio.book_my_show.Services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accio.book_my_show.Requests.AddMovieRequest;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")

    public ResponseEntity<String> addMovie(@Valid @RequestBody AddMovieRequest addMovieRequest){
            String ans= movieService.addMovie(addMovieRequest);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @PutMapping("/updateMovieRatingAndDuration")
    public ResponseEntity<String> updateMovieRatingAndDuration (
            @Valid @RequestBody UpdateRatingAndDuration updateRatingAndDuration){

            String ans= movieService.updateMovieRatingAndDuration(updateRatingAndDuration);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @GetMapping("/getMovieList")
    public ResponseEntity<List<GetMovieResponse>> getMovieList(){

            List<GetMovieResponse> ans= movieService.getMovieResponseList();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @DeleteMapping("/deleteMovie")
    public ResponseEntity<String> deleteMovie(@RequestParam Integer movieId){
            String ans= movieService.deleteMovie(movieId);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }
    @DeleteMapping("/deleteAllMovies")
    public ResponseEntity<String> deleteAllMovies(){
            String ans= movieService.clearMovies();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @GetMapping("/totalAmountByMovie")
    public ResponseEntity<?> totalAmountByMovie(@RequestParam("movieId") Integer movieId){
        long ans= movieService.getTotalAmountByMovie(movieId);
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }
    @GetMapping("/totalRevenueByPeriod")
    public ResponseEntity<?> totalRevenueByPeriod(@RequestParam("movieId") Integer movieId,
                                                  @RequestParam(value = "startDate", required = false)LocalDate startDate,
                                                  @RequestParam(value = "endDate",required = false)LocalDate endDate){

        long ans= movieService.getTotalRevenueByPeriod(movieId,startDate,endDate);
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }
}
