package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Requests.DeleteShowRequest;
import com.accio.book_my_show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShowRequest")
    public ResponseEntity<String> addShowRequest(@RequestBody AddShowRequest addShowRequest){
        try{
            String ans=showService.addShow(addShowRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteShow")
    public ResponseEntity<String>  deleteShow(DeleteShowRequest deleteShowRequest){
        try {
            String ans=showService.deleteShow(deleteShowRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
