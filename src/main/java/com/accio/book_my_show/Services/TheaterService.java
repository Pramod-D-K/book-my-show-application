package com.accio.book_my_show.Services;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Enums.SeatType;
import com.accio.book_my_show.Exceptions.ResourceNotFoundException;
import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Models.TheaterSeat;
import com.accio.book_my_show.Repositories.TheaterRepository;
import com.accio.book_my_show.Repositories.TheaterSeatRepository;
import com.accio.book_my_show.Requests.AddTheaterRequest;
import com.accio.book_my_show.Requests.AddTheaterSeatRequest;
import com.accio.book_my_show.Requests.DeleteTheaterRequest;
import com.accio.book_my_show.Responses.GetMovieResponse;
import com.accio.book_my_show.Responses.GetTheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheater(AddTheaterRequest addTheaterRequest) {

        Theater theater= Theater.builder()
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
                .build();

        theater=theaterRepository.save(theater);
        return "Theater "+theater.getName()+" has been added";
    }

    public List<GetTheaterResponse> getTheaterResponseList(){
        List<Theater>theaterList=theaterRepository.findAll();
        if(theaterList.isEmpty()){
            throw new RuntimeException("Theater DataBase is Empty");
        }
        List<GetTheaterResponse> theaterResponses=new ArrayList<>();
        for (Theater theater:theaterList){
            GetTheaterResponse theaterResponse=GetTheaterResponse.builder()
                    .name(theater.getName())
                    .address(theater.getAddress())
                    .build();
            theaterResponses.add(theaterResponse);
        }
        return theaterResponses;
    }

    public String deleteTheater(DeleteTheaterRequest deleteTheaterRequest){

        Integer theaterId=deleteTheaterRequest.getTheaterId();
        if (theaterId == null) {
            throw new RuntimeException("Theater ID cannot be null!");
        }
        Optional<Theater>optional=theaterRepository.findById(theaterId);
        Theater theater=optional.orElseThrow(()-> new ResourceNotFoundException("Theater not present in this Id"));
        theaterRepository.deleteById(theaterId);
        return "Theater has been deleted";
    }

    public String deleteAllTheater(){
        theaterRepository.deleteAll();
        return "All Theaters were deleted";
    }
}
