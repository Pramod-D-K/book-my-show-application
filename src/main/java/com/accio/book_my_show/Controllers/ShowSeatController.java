package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Models.ShowSeat;
import com.accio.book_my_show.Requests.AddShoeSeatRequest;
import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Services.ShowSeatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/showSeat")
public class ShowSeatController {

    @Autowired
    private ShowSeatService showSeatService;

    @PostMapping("/addShowSeat")
    public ResponseEntity<String> addShowSeat(@Valid @RequestBody AddShoeSeatRequest addShoeSeatRequest){
            String ans=showSeatService.addShowSeat(addShoeSeatRequest);
            return ResponseEntity.ok().body(ans);
    }

}
