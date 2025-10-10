package com.accio.book_my_show.Services;

import com.accio.book_my_show.Generators.CustomTicketGenerator;
import com.accio.book_my_show.Models.*;
import com.accio.book_my_show.Repositories.*;
import com.accio.book_my_show.Requests.BookTicketRequest;
import com.accio.book_my_show.Responses.GetBookedTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
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

    public GetBookedTicketResponse bookTicket(BookTicketRequest bookTicketRequest) throws Exception{

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
        List<String> notBookedSeats1 =new ArrayList<>(requestedSeats);
        int totalAmount1 =0;
        boolean isPresent=false;
        for (String requestedSeat:requestedSeats){
            for (ShowSeat showSeat:showSeatList ){

                if(showSeat.getSeatNo().equals(requestedSeat)){
                    isPresent=true;
                    if(showSeat.getIsAvailable()==Boolean.TRUE){
                        totalAmount1 = totalAmount1 +showSeat.getPrice();
                        showSeat.setIsAvailable(Boolean.FALSE);
                        bookedSeats1.add(showSeat.getSeatNo());
                        notBookedSeats1.remove(requestedSeat);
                        showSeatRepository.save(showSeat);
                    }
                }
            }
        }

        if(!isPresent){
            throw new Exception("All seats you entered were not found in this Show");
        }
        if(bookedSeats1.isEmpty()){
            throw new Exception("All seats you entered were booked already");
        }

        Ticket ticket= Ticket.builder()
                .showDate(showDate1)
                .showTime(showTime1)
                .movieName(movie.getName())
                .theaterName(theater.getName())
                .theaterAddress(theater.getAddress())
                .totalAmount(totalAmount1)
                //.bookedSeats(bookedSeats1)
                .show(show1)
                .user(user1)
                .build();
        ticket=ticketRepository.save(ticket);

        String ansMsg="";
        if(new HashSet<>(bookedSeats1).containsAll(requestedSeats)){
            ansMsg="All seats were booked successfully";
        }else{
            ansMsg="All seats were not booked";

        }

        GetBookedTicketResponse ans= GetBookedTicketResponse.builder()
                .userName(user1.getUserName())
                .movieName(movieName)
                .theaterName(theater.getName())
                .theaterAddress(theater.getAddress())
                .showDate(showDate1)
                .showTime(showTime1)
                .totalAmount(totalAmount1)
                .bookedSeats(bookedSeats1)
                .notBookedSeats(notBookedSeats1)
                .status(ansMsg)
                .build();

        return  ans;
    }
}
