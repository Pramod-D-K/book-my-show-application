package com.accio.book_my_show.Controllers;

import com.accio.book_my_show.Requests.BookTicketRequest;
import com.accio.book_my_show.Responses.GetBookedTicketResponse;
import com.accio.book_my_show.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity<?> bookTicket(@RequestBody BookTicketRequest bookTicketRequest){
        try{
            String ans=ticketService.bookTicket(bookTicketRequest);
            return ResponseEntity.ok().body(ans);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
