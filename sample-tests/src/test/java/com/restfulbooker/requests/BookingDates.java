package com.restfulbooker.requests;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Faisal Khatri on 22/07/2022
 */
@Data
@Builder
public class BookingDates {
   private  String checkin;
   private String checkout;
}
