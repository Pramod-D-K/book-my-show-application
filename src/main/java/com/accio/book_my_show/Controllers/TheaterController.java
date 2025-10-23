package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.AddTheaterRequest;
import com.accio.book_my_show.Requests.AddTheaterSeatRequest;
import com.accio.book_my_show.Requests.DeleteMovieRequest;
import com.accio.book_my_show.Requests.DeleteTheaterRequest;
import com.accio.book_my_show.Responses.GetMovieResponse;
import com.accio.book_my_show.Responses.GetTheaterResponse;
import com.accio.book_my_show.Services.TheaterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addTheater")
    public ResponseEntity<String> addTheater(@Valid @RequestBody AddTheaterRequest addTheaterRequest){
            String ans= theaterService.addTheater(addTheaterRequest);
            return ResponseEntity.accepted().body(ans);
    }

    @GetMapping("/getTheaterList")
    public ResponseEntity<List<GetTheaterResponse>> getTheaterList(){

            List<GetTheaterResponse> ans=theaterService.getTheaterResponseList();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @DeleteMapping("/deleteTheater")
    public ResponseEntity<String> deleteTheater(@Valid @RequestBody DeleteTheaterRequest deleteTheaterRequest){
            String ans= theaterService.deleteTheater(deleteTheaterRequest);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @DeleteMapping("/deleteAllTheaters")
    public ResponseEntity<String> deleteAllTheaters(){

            String ans= theaterService.deleteAllTheater();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }
}
