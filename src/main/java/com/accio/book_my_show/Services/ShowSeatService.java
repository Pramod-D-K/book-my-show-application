package com.accio.book_my_show.Services;

import com.accio.book_my_show.Enums.SeatStatus;
import com.accio.book_my_show.Enums.SeatType;
import com.accio.book_my_show.Models.Show;
import com.accio.book_my_show.Models.ShowSeat;
import com.accio.book_my_show.Models.Theater;
import com.accio.book_my_show.Models.TheaterSeat;
import com.accio.book_my_show.Repositories.ShowRepository;
import com.accio.book_my_show.Repositories.ShowSeatRepository;
import com.accio.book_my_show.Repositories.TheaterRepository;
import com.accio.book_my_show.Requests.AddShoeSeatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowSeatService {
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;

    public String addShowSeat(AddShoeSeatRequest addShoeSeatRequest)throws Exception{

        Integer showId= addShoeSeatRequest.getShowId();
        Optional<Show>optionalShow=showRepository.findById(showId);
        Show show= optionalShow.orElseThrow(()-> new Exception("Show not found By this Id"));

        Theater theater= show.getTheater();

        List<ShowSeat> showSeatList= new ArrayList<>();

        List<TheaterSeat>theaterSeatList=theater.getTheaterSeatList();

        for (TheaterSeat theaterSeat: theaterSeatList){
            if(theaterSeat.getSeatStatus().equals(SeatStatus.GOOD)){
                ShowSeat showSeat= ShowSeat.builder()
                        .seatNo(theaterSeat.getSeatNo())
                        .seatType(theaterSeat.getSeatType())
                        .show(show)
                        .isAvailable(Boolean.TRUE)
                        .build();
                if(theaterSeat.getSeatType().equals(SeatType.CLASSIC)){
                    showSeat.setPrice(addShoeSeatRequest.getPriceOfClassicSeat());
                    showSeatRepository.save(showSeat);
                    showSeatList.add(showSeat);
                }else{
                    showSeat.setPrice(addShoeSeatRequest.getPriceOfPremiumSeat());
                    showSeatRepository.save(showSeat);
                    showSeatList.add(showSeat);
                }

            }
        }
//        List<ShowSeat>oldShowSeatList=new ArrayList<>()
//        oldShowSeatList.addAll(showSeatList);
        show.setShowSeatList(showSeatList);
        showRepository.save(show);
        //showSeatRepository.saveAll(showSeatList);
        return "Show Seats has been generated for given show Id";
    }
}
