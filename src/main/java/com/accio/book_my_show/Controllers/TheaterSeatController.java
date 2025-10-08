package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Repositories.TheaterSeatRepository;
import com.accio.book_my_show.Requests.AddTheaterSeatRequest;
import com.accio.book_my_show.Requests.UpdateSeatStatus;
import com.accio.book_my_show.Services.TheaterSeatService;
import com.accio.book_my_show.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theaterSeats")
public class TheaterSeatController {
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private TheaterSeatService theaterSeatService;
    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    @PostMapping("/addTheaterSeats")
    public ResponseEntity<String> addTheaterSeats(@RequestBody AddTheaterSeatRequest addTheaterSeatRequest){
        try {
            String ans=theaterSeatService.addTheaterSeatRequest(addTheaterSeatRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/updateTheaterSeatStatus")
    public ResponseEntity<String> changeTheaterSeatStatus(@RequestBody UpdateSeatStatus updateSeatStatus){
        try {
            int ans =theaterSeatService.changeTheaterSeatStatus(updateSeatStatus);
            return ResponseEntity.ok().body("Seat updated  " +ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
