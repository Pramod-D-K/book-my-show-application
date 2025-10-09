package com.accio.book_my_show.Services;

import com.accio.book_my_show.Models.*;
import com.accio.book_my_show.Repositories.*;
import com.accio.book_my_show.Requests.BookTicketRequest;
import com.accio.book_my_show.Responses.GetBookedTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

        LocalDate showDate1 = bookTicketRequest.getShowDate();
        LocalTime showTime1 =bookTicketRequest.getShowTime();
        Integer theaterId= bookTicketRequest.getTheaterId();
        Optional<Theater>optionalTheater=theaterRepository.findById(theaterId);
        Theater theater= optionalTheater.orElseThrow(()-> new Exception("Theater not found by this Id"));

        String movieName= bookTicketRequest.getMovieName();
        Optional<Movie>optionalMovie=movieRepository.getMovie(movieName);
        Movie movie= optionalMovie.orElseThrow(()-> new Exception("Movie not found by this Id"));

        String mobileNo=bookTicketRequest.getMobileNo();
        Optional<User>optionalUser=userRepository.findUserByMobileNo(mobileNo);
        User user1 =optionalUser.orElseThrow(()-> new Exception("User not found"));

        Optional<Show>optionalShow=showRepository.getShow(
                showDate1,
                showTime1,
                movie.getMovieId(),
                theater.getTheaterId());
        Show show1 =optionalShow.orElseThrow(()-> new Exception("Show not found by this show1 time and date"));
        Integer showId = show1.getShowId();
        List<ShowSeat>showSeatList= showSeatRepository.findAllByShow_ShowId(showId);
        List<String> requestedSeats=bookTicketRequest.getRequestSeats();

        List<String> bookedSeats1 = new ArrayList<>();
        List<String> notBookedSeats1 =requestedSeats;
        int totalAmount1 =0;
        for (String requestedSeat:requestedSeats){
            for (ShowSeat showSeat:showSeatList ){

                if(showSeat.getSeatNo().equals(requestedSeat)){
                    if(showSeat.getIsAvailable()==Boolean.TRUE){
                        totalAmount1 = totalAmount1 +showSeat.getPrice();
                        showSeat.setIsAvailable(Boolean.FALSE);
                        bookedSeats1.add(showSeat.getSeatNo());
                    }
                    else{
                        notBookedSeats1.remove(requestedSeat);
                    }
                }
            }
        }

        if(notBookedSeats1.equals(requestedSeats)){
            throw new Exception("All seats you entered were booked already");
        }

        Ticket ticket= Ticket.builder()
                .showDate(showDate1)
                .showTime(showTime1)
                .movieName(movie.getName())
                .theaterNameAndAddress(theater.getName()+" "+ theater.getAddress())
                .totalAmount(totalAmount1)
                .bookedSeats(bookedSeats1)
                .show(show1)
                .user(user1)
                .build();
        ticket=ticketRepository.save(ticket);

        String ansMsg="";
        if(notBookedSeats1.isEmpty()){
            ansMsg="All seats were booked successfully";
        }else{
            ansMsg="All seats were not booked successfully";

        }

        GetBookedTicketResponse ans= GetBookedTicketResponse.builder()
                .userName(user1.getName())
                .movieName(movieName)
                .theaterName(theater.getName())
                .theaterAddress(theater.getAddress())
                .showDate(showDate1)
                .showTime(showTime1)
                .totalAmount(totalAmount1)
                .bookedSeats(bookedSeats1)
                .notBookedSeats(notBookedSeats1)
                .build();

        return  ans.toString()+ansMsg;
    }
}
