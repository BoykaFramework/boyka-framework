package com.restfulbooker.requests;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Faisal Khatri on 22/07/2022
 */
@Data
@Builder
public class BookingData {
    private String firstname;
    private String lastname;
    private int totalprice;
    private  boolean depositpaid;
    private  BookingDates bookingdates;
    private  String additionalneeds;
}
