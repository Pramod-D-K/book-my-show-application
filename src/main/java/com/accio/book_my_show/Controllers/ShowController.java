package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.AddShowRequest;
import com.accio.book_my_show.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/AddShowRequest")
    public ResponseEntity<String> addShowRequest(AddShowRequest addShowRequest){
        try{
            String ans=showService.addShow(addShowRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
