package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.UpdateRatingAndDuration;
import com.accio.book_my_show.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.accio.book_my_show.Requests.AddMovieRequest;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody AddMovieRequest addMovieRequest){
        try{
            String ans= movieService.addMovie(addMovieRequest);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateMovieRatingAndDuration")
    public ResponseEntity<String> updateMovieRatingAndDuration (
            @RequestBody UpdateRatingAndDuration updateRatingAndDuration){
        try{
            String ans= movieService.updateMovieRatingAndDuration(updateRatingAndDuration);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
