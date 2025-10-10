package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Requests.DeleteShowRequest;
import com.accio.book_my_show.Services.ShowService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/deleteShow")
    public ResponseEntity<String>  deleteShow(DeleteShowRequest deleteShowRequest){
            String ans=showService.deleteShow(deleteShowRequest);
            return ResponseEntity.ok().body(ans);
    }
}
