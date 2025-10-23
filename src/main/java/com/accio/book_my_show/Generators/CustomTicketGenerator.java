package com.accio.book_my_show.Generators;

import com.accio.book_my_show.Models.Ticket;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class CustomTicketGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (!(object instanceof Ticket ticket)) {
            throw new IllegalArgumentException("CustomTicketGenerator can only be used for Ticket entities");
        }

        String movieName = ticket.getMovieName() != null && ticket.getMovieName().length() >= 2
                ? ticket.getMovieName().substring(0, 2).toUpperCase() : "MV";

        String theaterName = ticket.getTheaterName() != null && ticket.getTheaterName().length() >= 2
                ? ticket.getTheaterName().substring(0, 2).toUpperCase() : "TH";

        String theaterAddress = ticket.getTheaterAddress() != null && ticket.getTheaterAddress().length() >= 4
                ? ticket.getTheaterAddress().substring(0, 2).toUpperCase(): "AD";

        LocalDate showDate = ticket.getShowDate() != null ? ticket.getShowDate() : LocalDate.now();
        LocalTime showTime = ticket.getShowTime() != null ? ticket.getShowTime() : LocalTime.now();

        String day = String.format("%02d", showDate.getDayOfMonth());
        String hour = String.format("%02d", showTime.getHour());

        String prefix = movieName + theaterName + theaterAddress + day + hour;
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5).toUpperCase();

        return prefix + random;
    }
}
