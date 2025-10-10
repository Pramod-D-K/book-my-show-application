package com.accio.book_my_show.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private LocalDateTime timeStamp;

    private String message;

    private String details;
}
