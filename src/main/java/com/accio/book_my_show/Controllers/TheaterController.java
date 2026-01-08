package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Repositories.TicketRepository;
import com.accio.book_my_show.Requests.AddTheaterRequest;
import com.accio.book_my_show.Requests.AddTheaterSeatRequest;
import com.accio.book_my_show.Requests.DeleteMovieRequest;
import com.accio.book_my_show.Requests.DeleteTheaterRequest;
import com.accio.book_my_show.Responses.GetMovieResponse;
import com.accio.book_my_show.Responses.GetTheaterResponse;
import com.accio.book_my_show.Services.TheaterService;
import com.accio.book_my_show.Services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    private TheaterService theaterService;
    @Autowired
    private TicketService ticketService;



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
    public ResponseEntity<String> deleteTheater(@Valid @RequestParam Integer theaterId){
            String ans= theaterService.deleteTheater(theaterId);
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @DeleteMapping("/deleteAllTheaters")
    public ResponseEntity<String> deleteAllTheaters(){

            String ans= theaterService.deleteAllTheater();
            return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @GetMapping("/totalRevenueByTheater")
    public ResponseEntity<?> totalRevenueByTheater(@RequestParam Integer theaterId,
                                                   @RequestParam (value = "startDate", required = false)LocalDate startDate,
                                                   @RequestParam(value = "endDate", required = false) LocalDate endDate){

        long ans= ticketService.getRevenueByTheater(theaterId,startDate,endDate);
        return ResponseEntity.status(HttpStatus.OK).body(ans);
    }

    @GetMapping("/listOfTheaters")
    public List<Theater> listOfTheaters(@RequestParam String movieName,
                                        @RequestParam String movieDate) {
        List<Theater> ans= null;
        try {
            ans = theaterService.theaterList(movieName,LocalDate.parse(movieDate));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ans;
    }
}
