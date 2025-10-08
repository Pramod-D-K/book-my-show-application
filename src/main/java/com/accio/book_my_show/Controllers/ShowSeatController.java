package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Models.ShowSeat;
import com.accio.book_my_show.Requests.AddShoeSeatRequest;
import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Services.ShowSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/showSeat")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    @PostMapping("/addShowSeat")
    public ResponseEntity<String> addShowSeat(@RequestBody AddShoeSeatRequest addShoeSeatRequest){
        try {
            String ans=showSeatService.addShowSeat(addShoeSeatRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
