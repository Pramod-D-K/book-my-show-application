package com.accio.book_my_show.Responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTheaterResponse {

    private String name;

    private String address;
}
