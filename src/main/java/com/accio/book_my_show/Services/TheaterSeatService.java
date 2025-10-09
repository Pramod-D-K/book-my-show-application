package com.accio.book_my_show.Services;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Enums.SeatType;
import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Models.TheaterSeat;
import com.accio.book_my_show.Repositories.TheaterRepository;
import com.accio.book_my_show.Repositories.TheaterSeatRepository;
import com.accio.book_my_show.Requests.AddTheaterSeatRequest;
import com.accio.book_my_show.Requests.UpdateSeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TheaterSeatService {
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private TheaterSeatRepository theaterSeatRepository;

    public String addTheaterSeatRequest(AddTheaterSeatRequest addTheaterSeatRequest) throws Exception{

        int theaterId= addTheaterSeatRequest.getTheaterId();
        Optional<Theater> optional=theaterRepository.findById(theaterId);
        Theater theater=optional.orElseThrow(()-> new Exception("Theater not found by this Id"));

        List<TheaterSeat>theaterSeatList=theaterSeatRepository.findAll();
        for (TheaterSeat theaterSeat:theaterSeatList){
            if(theaterSeat.getTheater().equals(theater)){
                return "Seats of this Theater already added";
            }
        }
        int noOfClaSeats=addTheaterSeatRequest.getNoOfClassicSeats();
        int noOfPreSeats= addTheaterSeatRequest.getNoOfPremiumSeats();
        int noOfColumns=addTheaterSeatRequest.getNoOfColumns();

        char ch='A';
        int currentRow=1;
        int count=1;
        List<TheaterSeat> theaterSeats= new ArrayList<>();
        while (count<=noOfClaSeats){
            String seat= currentRow+""+ch;
            TheaterSeat theaterSeat= TheaterSeat.builder()
                    .seatNo(seat)
                    .seatType(SeatType.CLASSIC)
                    .seatStatus(SeatStatus.GOOD)
                    .theater(theater)
                    .build();
            theaterSeats.add(theaterSeat);
            ch++;
            if(count%noOfColumns==0){
                currentRow++;
                ch='A';
            }
            count++;
        }

        if(noOfClaSeats%noOfColumns!=0){
            currentRow=currentRow+1;
        }
        ch='A';
        count=1;
        while (count<=noOfPreSeats){
            String seat= currentRow+""+ch;
            TheaterSeat theaterSeat= TheaterSeat.builder()
                    .seatNo(seat)
                    .seatType(SeatType.PREMIUM)
                    .seatStatus(SeatStatus.GOOD)
                    .theater(theater)
                    .build();
            theaterSeats.add(theaterSeat);
            ch++;
            if(count%noOfColumns==0){
                currentRow++;
                ch='A';
            }
            count++;
        }
        theater.setTheaterSeatList(theaterSeats);
        theaterRepository.save(theater);
//      I skipped to save the theaterSeats because it is bidirectional mapping no need to save both of them
        //because of cascading written in parent table;
//      theaterSeatRepository.saveAll(theaterSeats);
        return "Seats of theater "+theater.getName()+" has been added";
    }

    public int changeTheaterSeatStatus(UpdateSeatStatus updateSeatStatus)throws Exception{
        Integer seatId=updateSeatStatus.getSeatId();
        SeatStatus seatStatus=updateSeatStatus.getSeatStatus();
        Optional<TheaterSeat>optionalTheaterSeat=theaterSeatRepository.findById(seatId);
        TheaterSeat theaterSeat=optionalTheaterSeat.orElseThrow(()-> new Exception("TheaterSeat not found by this Id"));
        int ans=theaterSeatRepository.updateTheaterSeatStatus(seatId,seatStatus);
        return  ans;
    }
}
