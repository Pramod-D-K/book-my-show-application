package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.AddTheaterRequest;
import com.accio.book_my_show.Requests.AddTheaterSeatRequest;
import com.accio.book_my_show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;

    @PostMapping("/addTheater")
    public ResponseEntity<String> addTheater(@RequestBody AddTheaterRequest addTheaterRequest){
        try {
            String ans= theaterService.addTheater(addTheaterRequest);
            return ResponseEntity.accepted().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAllTheaters")
    public ResponseEntity<String> deleteAllTheaters(){
        try {
            String ans= theaterService.clearTheater();
            return ResponseEntity.accepted().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addTheaterSeats")
    public  ResponseEntity<String> addTheaterSeats(@RequestBody AddTheaterSeatRequest addTheaterSeatRequest){
        try {
            String ans=theaterService.addTheaterSeatRequest(addTheaterSeatRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
