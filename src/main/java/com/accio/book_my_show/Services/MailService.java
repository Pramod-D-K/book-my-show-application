package com.accio.book_my_show.Services;

import com.accio.book_my_show.Models.Ticket;
import com.accio.book_my_show.Models.User;
import com.accio.book_my_show.Requests.BookTicketRequest;
import com.accio.book_my_show.Responses.GetBookedTicketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendWelcomeMail(User user) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Welcome to Book My Show Application");
        message.setFrom("springacciojob12@gmail.com");
        message.setTo(user.getEmailId());

        String body = "Hi " + user.getUserName() + "! \n\n"
                + "Welcome to Book My Show Application! 🎬\n"
                + "Feel free to browse the latest movies.\n\n"
                + "Use Coupon: START100 to get ₹100 instant discount.\n\n"
                + "Regards,\nBookMyShow Team";

        message.setText(body);
        javaMailSender.send(message);
    }

    public  void sendTicketConfirmation(User user, String ticketNo, GetBookedTicketResponse details){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Confirmation Book My Show Application");
        message.setFrom("springacciojob12@gmail.com");
        message.setTo(user.getEmailId());
        String information ="";
        if(!details.getNotBookedSeats().isEmpty()){
            information="Unfortunately We are unable to book those Show Seats+ \n"
                    +details.getNotBookedSeats()+"\n"
                    +"Please Book other Show Seats \n\n";
        }

        String body = "Hi " + user.getUserName() + "!\n\n"
                + "🎫 Your ticket has been successfully confirmed for the movie: "
                + details.getMovieName() + " 🎬\n\n"
                + "Booking Details:\n"
                + "Theatre: " + details.getTheaterName() + "\n"
                + "Theatre Address: " + details.getTheaterAddress() + "\n"
                + "Date: " + details.getShowDate() + "\n"
                + "Time: " + details.getShowTime() + "\n"
                + "Seats: " + details.getBookedSeats() + "\n"
                + "Total Amount: ₹" + details.getTotalAmount() + "\n\n"
                +information +"\n\n"
                +"Ticket Number :"+ ticketNo +"\n"
                +"Don't share with any one"
                + "Thank you for booking with Book My Show Application!\n"
                + "Feel free to browse the latest movies and offers.\n\n"
                + "Regards,\n"
                + "BookMyShow Team 🍿";

        message.setText(body);
        javaMailSender.send(message);
    }
    public void sendNotSeatBookedMail(User user, BookTicketRequest details){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(user.getEmailId());
        message.setFrom("springacciojob12@gmail.com");
        message.setSubject("Booking Tickets in Book My Show Application");
        String body = "Hi " + user.getUserName() + "!\n\n"
                + "🎫 Your ticket not confirmed for the movie: "
                + details.getMovieName() + " 🎬\n\n"
                +"Entered Seats are already booked \n"
                +"Please Book the other Seats \n"
                + "Thanks for visiting Book My Show Application!\n"
                + "Feel free to browse the latest movies and offers.\n\n"
                + "Regards,\n"
                + "BookMyShow Team 🍿";
        message.setText(body);
        javaMailSender.send(message);
    }
}
