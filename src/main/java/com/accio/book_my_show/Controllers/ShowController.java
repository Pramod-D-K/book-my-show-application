package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Enums.MovieGenre;
import com.accio.book_my_show.Enums.MovieLanguage;
import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Requests.DeleteShowRequest;
import com.accio.book_my_show.Responses.GetShowResponse;
import com.accio.book_my_show.Services.ShowService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShowRequest")
    public ResponseEntity<String> addShowRequest(@Valid @RequestBody AddShowRequest addShowRequest){
            String ans=showService.addShow(addShowRequest);
            return ResponseEntity.ok().body(ans);
    }

    @GetMapping("/getShowsByMovieName/{name}")
    public ResponseEntity<?> getShowsByMovieName(@PathVariable("name") String movieName){
        List<GetShowResponse> getShowResponse=showService.getShowByMovieName(movieName);
        return ResponseEntity.ok().body(getShowResponse);
    }

    @GetMapping("/getShowaByTheaterId/{theaterId}")
    public ResponseEntity<?> getShowaByTheaterId(@PathVariable("theaterId") Integer theaterId){
        List<GetShowResponse> getShowResponse=showService.getShowByTheater(theaterId);
        return ResponseEntity.ok().body(getShowResponse);
    }
    @GetMapping("/getShowByFilter")
    public ResponseEntity<?> getShowsByFilter(@RequestParam(value = "movieName",required = false) String movieName,
                                                           @RequestParam(value = "city",required = false) String city,
                                                           @RequestParam(value = "showDate",required = false)LocalDate showDate,
                                                           @RequestParam(value = "showTime",required = false)LocalTime showTime,
                                                           @RequestParam(value = "language",required = false)MovieLanguage language,
                                                           @RequestParam(value = "genre",required = false)MovieGenre genre){
        List<GetShowResponse> getShowResponse=showService.getShowByFilter(movieName,city,showDate,showTime,language,genre);
        return ResponseEntity.ok().body(getShowResponse);
    }


    @DeleteMapping("/deleteShow")
    public ResponseEntity<String>  deleteShow(DeleteShowRequest deleteShowRequest){
            String ans=showService.deleteShow(deleteShowRequest);
            return ResponseEntity.ok().body(ans);
    }
}
