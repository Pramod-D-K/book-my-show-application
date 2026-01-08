package com.accio.book_my_show.Services;

import com.accio.book_my_show.Exceptions.ResourceNotFoundException;
import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Repositories.ShowRepository;
import com.accio.book_my_show.Repositories.TheaterRepository;
import com.accio.book_my_show.Repositories.TheaterSeatRepository;
import com.accio.book_my_show.Requests.AddTheaterRequest;
import com.accio.book_my_show.Responses.GetTheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    @Autowired
    private ShowRepository showRepository;

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

    public String deleteTheater(Integer theaterId){
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

    public List<Theater> theaterList(String movieName, LocalDate showDate) throws Exception{
        List<Show> showList = showRepository.findByMovie_NameAndShowDate(movieName,showDate);
        List<Theater> ans= new ArrayList<>();
        if(showList.isEmpty()){
            throw new ResourceNotFoundException("Theaters not found");
        }
        for(Show show: showList){
            Theater theater=show.getTheater();
            ans.add(theater);
        }
        return ans;
    }
}
