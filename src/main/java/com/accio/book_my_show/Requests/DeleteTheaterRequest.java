package com.accio.book_my_show.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class DeleteTheaterRequest {
    private Integer theaterId;
}
